package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sub_project_mstr database table.
 * 
 */
@Entity
@Table(name="SUB_PROJECT_MSTR")
@NamedQueries ({
	@NamedQuery(name="SubProjectMstr.findAll", query="SELECT s FROM SubProjectMstr s"),
	@NamedQuery(name="SubProjectMstr.findByProject", query="SELECT s FROM SubProjectMstr s where s.project.id = :projectId" )
})
public class SubProjectMstr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date actualEndDate;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date estimatedEndDate;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to SubProjectEmployee
	@OneToMany(mappedBy="subProjectMstr")
	private List<SubProjectEmployee> subProjectEmployees;

	//bi-directional many-to-one association to SubProjectLabour
	@OneToMany(mappedBy="subProjectMstr")
	private List<SubProjectLabour> subProjectLabours;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projectId")
	private Project project;

	public SubProjectMstr() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActualEndDate() {
		return this.actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEstimatedEndDate() {
		return this.estimatedEndDate;
	}

	public void setEstimatedEndDate(Date estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<SubProjectEmployee> getSubProjectEmployees() {
		return this.subProjectEmployees;
	}

	public void setSubProjectEmployees(List<SubProjectEmployee> subProjectEmployees) {
		this.subProjectEmployees = subProjectEmployees;
	}

	public SubProjectEmployee addSubProjectEmployee(SubProjectEmployee subProjectEmployee) {
		getSubProjectEmployees().add(subProjectEmployee);
		subProjectEmployee.setSubProjectMstr(this);

		return subProjectEmployee;
	}

	public SubProjectEmployee removeSubProjectEmployee(SubProjectEmployee subProjectEmployee) {
		getSubProjectEmployees().remove(subProjectEmployee);
		subProjectEmployee.setSubProjectMstr(null);

		return subProjectEmployee;
	}

	public List<SubProjectLabour> getSubProjectLabours() {
		return this.subProjectLabours;
	}

	public void setSubProjectLabours(List<SubProjectLabour> subProjectLabours) {
		this.subProjectLabours = subProjectLabours;
	}

	public SubProjectLabour addSubProjectLabour(SubProjectLabour subProjectLabour) {
		getSubProjectLabours().add(subProjectLabour);
		subProjectLabour.setSubProjectMstr(this);

		return subProjectLabour;
	}

	public SubProjectLabour removeSubProjectLabour(SubProjectLabour subProjectLabour) {
		getSubProjectLabours().remove(subProjectLabour);
		subProjectLabour.setSubProjectMstr(null);

		return subProjectLabour;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}