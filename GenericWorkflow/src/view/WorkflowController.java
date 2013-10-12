package view;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.DAO;
import dao.IWorkflowDao;
import domain.Node;
import domain.Workflow;
import domain.WorkflowInstance;

@Controller
public class WorkflowController {
	
	@Autowired
	private IWorkflowDao workflowDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listworkflows", method = RequestMethod.GET)
	public String show(Model model) {
		List<Workflow> workflows = (List<Workflow>) workflowDao.findAll(Workflow.class);
		model.addAttribute("workflows",workflows);
		return "listworkflows";
	}
	
	@RequestMapping(value = "/newworkflow", method = RequestMethod.GET)
	public String newworkflow(Model model) {

		return "newworkflow";
	}
	
	@RequestMapping(value = "/newnode", method = RequestMethod.GET)
	public String newnode(Model model) {
		
		return "newnode";
	}
	
	@RequestMapping(value = "/showworkflow/{workflowname}", method = RequestMethod.GET)
	public String startWorkflow(@PathVariable String workflowname,Model model) {
		List<String> actions = workflowDao.getWorkflowFirstNodeActions(workflowname);
		model.addAttribute("actions",actions);
		model.addAttribute("instanceId",0);
		model.addAttribute("workflowname",workflowname);
		return "showworkflow";
	}
	
	@RequestMapping(value = "/editworkflow/{workflowid}", method = RequestMethod.GET)
	public ModelAndView editWorkflow(@PathVariable Integer workflowid,Model model) {
		Workflow workflow = (Workflow) workflowDao.findByID(Workflow.class, workflowid);
		return new ModelAndView("editworkflow", "command", workflow);
	}
	
	@RequestMapping(value = "/saveworkflow", method = RequestMethod.POST)
	public String saveWorkflow(@ModelAttribute("SpringWeb") Workflow workflow, ModelMap model) {
		workflowDao.saveOrUpdate(workflow);
		return "result";
	}
	
	@RequestMapping(value = "/editnode/{nodeid}", method = RequestMethod.GET)
	public ModelAndView editNode(@PathVariable Integer nodeid,Model model) {
		Node node = (Node) workflowDao.findByID(Node.class, nodeid);
		return new ModelAndView("editnode", "command", node);
	}
	
	@RequestMapping(value = "/savenode", method = RequestMethod.POST)
	public String saveNode(@ModelAttribute("SpringWeb") Node node, ModelMap model) {
		workflowDao.saveOrUpdate(node);
		return "result";
	}
	
	@RequestMapping(value = "/showactions/{instanceid}", method = RequestMethod.GET)
	public String showActions(@PathVariable Integer instanceid, Model model) {
		List<String> actions = workflowDao.getWorkflowCurrentNodeActions(null, instanceid);
		
		WorkflowInstance instance = (WorkflowInstance) DAO.findByID(WorkflowInstance.class, instanceid);
		model.addAttribute("actions",actions);
		model.addAttribute("instanceId",instanceid);
		model.addAttribute("workflowname",instance.getWorkflow().getName());
		return "showworkflow";
	}
	
	@RequestMapping(value = "/showstatus/{instanceid}", method = RequestMethod.GET)
	public String showStatus(@PathVariable Integer instanceid, Model model) {
		WorkflowInstance instance = (WorkflowInstance) DAO.findByID(WorkflowInstance.class, instanceid);
		
		if(instance.getCurrentState().getNode()!=null){
			model.addAttribute("status",instance.getCurrentState().getNode().getName());
		}else{
			model.addAttribute("status","This workflow is completed");
		}
		model.addAttribute("instanceId",instanceid);
		model.addAttribute("workflowname",instance.getWorkflow().getName());
		return "showstatus";
	}
	
	@RequestMapping(value = "/takeaction/{workflowname}/{workflowinstanceid}/{action}", method = RequestMethod.GET)
	public String takeAction(@PathVariable String workflowname,@PathVariable Integer workflowinstanceid,@PathVariable String action,Model model) {
		Integer instanceId = null;
		try {
			instanceId = workflowDao.changeState(workflowname, workflowinstanceid, action, null, "jad", "action takes");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("instanceId",instanceId);
		return "resultworkflow";
	}
}