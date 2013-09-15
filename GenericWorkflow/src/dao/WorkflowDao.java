package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import domain.NodeAction;


public class WorkflowDao extends GenericDao implements IWorkflowDao {

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
}