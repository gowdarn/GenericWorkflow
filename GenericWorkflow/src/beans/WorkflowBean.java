package beans;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import context.AppContext;
import dao.IWorkflowDao;
import domain.Action;
import domain.Node;
import domain.Workflow;

@Component("workflowBean")
@Scope
@ManagedBean
public class WorkflowBean implements Serializable{
	
	private IWorkflowDao workflowDao;
	private Workflow workflow = new Workflow();
	private Node node= new Node();
	private Action action= new Action();
	
	private Integer workflowId;
	private Integer previousNodeId;
	private Integer nextNodeId;
	
	public WorkflowBean() {
		
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	
	public Node getNode() {
		return node;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}

	
	
	public void submit(){
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflow.setCreationDate(new Date());
		workflowDao.saveOrUpdate(workflow);
	}
	
	public void savenode(){
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		node.setWorkflow(new Workflow(workflowId));
		if(previousNodeId != 0){
			node.setPreviousNode(new Node(previousNodeId));
		}
		if(nextNodeId != 0){
			node.setNextNode(new Node(nextNodeId));
		}
		workflowDao.saveOrUpdate(node);
	}
	public void saveaction(){
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.saveOrUpdate(action);
	}
	public void startWorkflow(String workflowName) throws SecurityException, IllegalArgumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException{
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.changeState(workflowName, 0, "Submit", null, "jad", "no comments");
	}
	
	public void changeState(String workflowName, Integer workflowInstanceId, String actionName) throws SecurityException, IllegalArgumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException{
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.changeState(workflowName, workflowInstanceId, actionName, null, "employee 1", "all good");
	}
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
	public Integer getPreviousNodeId() {
		return previousNodeId;
	}
	public void setPreviousNodeId(Integer previousNodeId) {
		this.previousNodeId = previousNodeId;
	}
	public Integer getNextNodeId() {
		return nextNodeId;
	}
	public void setNextNodeId(Integer nextNodeId) {
		this.nextNodeId = nextNodeId;
	}
	    
}
