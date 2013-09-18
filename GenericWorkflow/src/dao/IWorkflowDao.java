package dao;

import java.util.List;

public interface IWorkflowDao {

	List<String> getWorkflowFirstNodeActions(String workflowName);

	Integer startNewWorkflow(String workflowName, String actionName, byte[] data,
			String user, String comments);

	List<String> getWorkflowCurrentNodeActions(String workflowName,
			Integer workflowInstanceId);

}
