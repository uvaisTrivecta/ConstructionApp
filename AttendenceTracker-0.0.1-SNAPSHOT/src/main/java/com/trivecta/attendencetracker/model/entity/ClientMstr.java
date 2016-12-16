package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the client_mstr database table.
 * 
 */
@Entity
@Table(name="CLIENT_MSTR")
@NamedQuery(name="ClientMstr.findAll", query="SELECT c FROM ClientMstr c")
public class ClientMstr implements Serializable {
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

	private String name;

	private String primaryContactName;

	private BigInteger primaryContactNo;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="addressId")
	private Address address;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="clientMstr")
	private List<Project> projects;

	public ClientMstr() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryContactName() {
		return this.primaryContactName;
	}

	public void setPrimaryContactName(String primaryContactName) {
		this.primaryContactName = primaryContactName;
	}

	public BigInteger getPrimaryContactNo() {
		return this.primaryContactNo;
	}

	public void setPrimaryContactNo(BigInteger primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setClientMstr(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setClientMstr(null);

		return project;
	}

}