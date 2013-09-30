package dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IWorkflowDao extends IGenericDao {

	List<String> getWorkflowFirstNodeActions(String workflowName);

	Integer startNewWorkflow(String workflowName, String actionName, byte[] data,
			String user, String comments) throws SecurityException, IllegalArgumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException;

	List<String> getWorkflowCurrentNodeActions(String workflowName,
			Integer workflowInstanceId);

	Integer changeState(String workflowName, Integer workflowInstanceId,
			String actionName, byte[] data, String user, String comments)
			throws SecurityException, NoSuchMethodException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, InstantiationException;

}
