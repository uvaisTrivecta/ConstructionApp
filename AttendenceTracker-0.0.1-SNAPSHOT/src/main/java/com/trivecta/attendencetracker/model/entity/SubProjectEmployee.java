package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sub_project_employee database table.
 * 
 */
@Entity
@Table(name="SUB_PROJECT_EMPLOYEE")
@NamedQuery(name="SubProjectEmployee.findAll", query="SELECT s FROM SubProjectEmployee s")
public class SubProjectEmployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	//bi-directional many-to-one association to SubProjectMstr
	@ManyToOne
	@JoinColumn(name="subProjectId")
	private SubProjectMstr subProjectMstr;

	public SubProjectEmployee() {
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

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SubProjectMstr getSubProjectMstr() {
		return this.subProjectMstr;
	}

	public void setSubProjectMstr(SubProjectMstr subProjectMstr) {
		this.subProjectMstr = subProjectMstr;
	}

}