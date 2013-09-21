package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("workflowBean")
@Scope
@ManagedBean
public class WorkflowBean implements Serializable{

	private String name;

    private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	    
}
