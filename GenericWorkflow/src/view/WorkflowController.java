package view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.IWorkflowDao;
import domain.Workflow;

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
}