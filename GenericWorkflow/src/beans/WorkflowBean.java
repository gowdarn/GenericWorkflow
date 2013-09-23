package beans;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import context.AppContext;
import dao.IWorkflowDao;
import domain.Node;
import domain.Workflow;

@Component("workflowBean")
@Scope
@ManagedBean
public class WorkflowBean implements Serializable{
	
	private IWorkflowDao workflowDao;
	private Workflow workflow = new Workflow();
	private Node node= new Node();
	
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

	
	
	public void submit(){
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.saveOrUpdate(workflow);
	}
	
	public void savenode(){
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.saveOrUpdate(node);
	}
	public void startWorkflow(String workflowName) throws SecurityException, IllegalArgumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException{
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.changeState(workflowName, 0, "Submit", null, "jad", "no comments");
	}
	
	public void changeState(String workflowName, Integer workflowInstanceId, String actionName) throws SecurityException, IllegalArgumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException{
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.changeState(workflowName, workflowInstanceId, actionName, null, "employee 1", "all good");
	}
	    
}
