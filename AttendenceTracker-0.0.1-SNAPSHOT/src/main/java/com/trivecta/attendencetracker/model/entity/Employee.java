package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="EMPLOYEE")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private String firstName;

	private String lastName;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String nickName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to Communication
	@ManyToOne
	@JoinColumn(name="communicationId")
	private Communication communication;

	//bi-directional many-to-one association to SubProjectEmployee
	@OneToMany(mappedBy="employee")
	private List<SubProjectEmployee> subProjectEmployees;

	//bi-directional many-to-one association to SubProjectLabour
	@OneToMany(mappedBy="employee")
	private List<SubProjectLabour> subProjectLabours;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Communication getCommunication() {
		return this.communication;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}

	public List<SubProjectEmployee> getSubProjectEmployees() {
		return this.subProjectEmployees;
	}

	public void setSubProjectEmployees(List<SubProjectEmployee> subProjectEmployees) {
		this.subProjectEmployees = subProjectEmployees;
	}

	public SubProjectEmployee addSubProjectEmployee(SubProjectEmployee subProjectEmployee) {
		getSubProjectEmployees().add(subProjectEmployee);
		subProjectEmployee.setEmployee(this);

		return subProjectEmployee;
	}

	public SubProjectEmployee removeSubProjectEmployee(SubProjectEmployee subProjectEmployee) {
		getSubProjectEmployees().remove(subProjectEmployee);
		subProjectEmployee.setEmployee(null);

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
		subProjectLabour.setEmployee(this);

		return subProjectLabour;
	}

	public SubProjectLabour removeSubProjectLabour(SubProjectLabour subProjectLabour) {
		getSubProjectLabours().remove(subProjectLabour);
		subProjectLabour.setEmployee(null);

		return subProjectLabour;
	}

}