package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import domain.Action;
import domain.Node;
import domain.NodeAction;
import domain.Workflow;
import domain.WorkflowInstance;
import domain.WorkflowState;


public class WorkflowDao extends GenericDao implements IWorkflowDao {

	@Override
	public List<String> getWorkflowCurrentNodeActions(String workflowName, Integer workflowInstanceId){
		
		if(workflowInstanceId == 0){
			return getWorkflowFirstNodeActions(workflowName);
		}
		
		WorkflowInstance workflowInstance = (WorkflowInstance) criteria(WorkflowInstance.class)
				.add(Restrictions.eq("id", workflowInstanceId))
				.uniqueResult();
		
		List<String> actions = new ArrayList<String>();
		for (NodeAction action : workflowInstance.getCurrentState().getNode().getActions()){
			actions.add(action.getAction().getName());
		}
		
		return actions;
	}
	
	public Integer changeState(String workflowName, Integer workflowInstanceId, String actionName, byte[] data, String user, String comments){
		if(workflowInstanceId == 0){
			return startNewWorkflow(workflowName, actionName, data, user, comments);
		}else{
			WorkflowInstance workflowInstance = (WorkflowInstance) criteria(WorkflowInstance.class)
					.add(Restrictions.eq("id", workflowInstanceId))
					.uniqueResult();
			
			Node currentNode = workflowInstance.getCurrentState().getNode();
			
			NodeAction nodeAction = (NodeAction) criteria(NodeAction.class)
					.createAlias("node", "node")
					.createAlias("action", "action")
					.add(Restrictions.eq("node.id", currentNode.getId()))
					.add(Restrictions.eq("action.name", actionName))
					.uniqueResult();
			
			//create next or previous workflow state
			WorkflowState workflowState = new WorkflowState();
			workflowState.setAction(getActionByName(actionName));
			workflowState.setDate(new Date());
			if(nodeAction.getDirection() == true){
				workflowState.setNode(currentNode.getNextNode());
			}else{
				workflowState.setNode(currentNode.getPreviousNode());
			}
			workflowState.setWorkflowInstance(workflowInstance);
			workflowState.setUser(user);
			workflowState.setComments(comments);
			
			Integer workflowStateId = insert(workflowState);
			workflowState.setId(workflowStateId);
			
			//assign current state to workflow instance
			workflowInstance.setCurrentState(workflowState);
			saveOrUpdate(workflowInstance);
			return workflowInstance.getId();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getWorkflowFirstNodeActions(String workflowName){
		
		List<NodeAction> nodeActions = criteria(NodeAction.class)
				.createAlias("node", "node")
				.createAlias("node.workflow", "workflow")
				.add(Restrictions.eq("workflow.name", workflowName))
				.add(Restrictions.eq("node.previousNode", null))
				.list();
		
		List<String> actions = new ArrayList<String>();
		for (NodeAction action : nodeActions){
			actions.add(action.getAction().getName());
		}
		
		return actions;
	}
	
	@Override
	public Integer startNewWorkflow(String workflowName, String actionName, byte[] data, String user, String comments){
		Workflow workflow = (Workflow) criteria(Workflow.class)
				.add(Restrictions.eq("name", workflowName)).uniqueResult();
		
		if(workflow != null){
			//create workflow instance
			WorkflowInstance workflowInstance = new WorkflowInstance();
			workflowInstance.setWorkflow(workflow);
			workflowInstance.setCreationDate(new Date());
			workflowInstance.setData(data);
			
			Integer id = insert(workflowInstance);
			workflowInstance.setId(id);
			
			//create first workflow state
			WorkflowState workflowState = new WorkflowState();
			workflowState.setAction(getActionByName(actionName));
			workflowState.setDate(new Date());
			workflowState.setNode(getWorkflowFirstNode(workflowName));
			workflowState.setWorkflowInstance(workflowInstance);
			workflowState.setUser(user);
			workflowState.setComments(comments);
			
			Integer workflowStateId = insert(workflowState);
			workflowState.setId(workflowStateId);
			
			//assign current state to workflow instance
			workflowInstance.setCurrentState(workflowState);
			saveOrUpdate(workflowInstance);
			return workflowInstance.getId();
		}
		
		return null;
	}
	
	public Action getActionByName(String actionName){
		Action action = (Action) criteria(Action.class)
				.add(Restrictions.eq("name", actionName)).uniqueResult();
		
		return action;
	}
	
	public Node getWorkflowFirstNode(String workflowName){
		Node node = (Node) criteria(Node.class)
				.createAlias("workflow", "workflow")
				.add(Restrictions.eq("workflow.name", workflowName))
				.add(Restrictions.eq("node.previousNode", null))
				.uniqueResult();
		
		return node;
	}
}