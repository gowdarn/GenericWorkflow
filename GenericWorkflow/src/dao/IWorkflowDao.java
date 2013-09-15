package dao;

import java.util.List;

public interface IWorkflowDao {

	List<String> getWorkflowFirstNodeActions(String workflowName);

}
