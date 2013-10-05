package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import context.AppContext;
import dao.IWorkflowDao;
import domain.Node;
import domain.Workflow;

@Component("constantsBean")
@Scope(value="session")
@ManagedBean
public class ConstantsBean implements Serializable{
	
	private List<Workflow> workflows = new ArrayList<Workflow>();	
	private List<Node> nodes = new ArrayList<Node>();	
	
	private IWorkflowDao workflowDao;
	
	public ConstantsBean() {
		loadConstants();		
	}
	
	@SuppressWarnings("unchecked")
	public void loadConstants(){
		workflowDao = (IWorkflowDao) AppContext.getBean("workflowDao");
		
		setWorkflows((List<Workflow>) workflowDao.findAll(Workflow.class));
		setNodes((List<Node>) workflowDao.findAll(Node.class));
	}

	public List<Workflow> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(List<Workflow> workflows) {
		this.workflows = workflows;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
