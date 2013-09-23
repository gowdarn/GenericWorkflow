package beans;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import context.AppContext;

import dao.IWorkflowDao;
import domain.Workflow;

@Component("workflowBean")
@Scope
@ManagedBean
public class WorkflowBean implements Serializable{
	
	private IWorkflowDao workflowDao;
	private Workflow workflow = new Workflow();
	
	public WorkflowBean() {
		
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	
	public void submit(){
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		workflowDao.saveOrUpdate(workflow);
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
