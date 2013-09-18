package domain;

// Generated Aug 12, 2013 2:56:13 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * GwNode generated by hbm2java
 */
@Entity
@Table(name = "gw_node", catalog = "genericworkflow")
public class Node implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -289636178603899703L;
	
	private Integer id;
	private Workflow workflow;
	private Node previousNode;
	private Node nextNode;
	private String name;
	private String className;
	private Set<NodeAction> actions = new HashSet<NodeAction>(0);

	public Node() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workflow", nullable = false)
	public Workflow getWorkflow() {
		return this.workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "previous_node")
	public Node getPreviousNode() {
		return this.previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "next_node")
	public Node getNextNode() {
		return this.nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "class_name", length = 45)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gwNode")
	public Set<NodeAction> getActions() {
		return this.actions;
	}

	public void setActions(Set<NodeAction> actions) {
		this.actions = actions;
	}
}
