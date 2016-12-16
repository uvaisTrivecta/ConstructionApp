package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@Table(name="PROJECT")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
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

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="addressId")
	private Address address;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="cityId")
	private City city;

	//bi-directional many-to-one association to ClientMstr
	@ManyToOne
	@JoinColumn(name="clientId")
	private ClientMstr clientMstr;

	//bi-directional many-to-one association to SubProjectMstr
	@OneToMany(mappedBy="project")
	private List<SubProjectMstr> subProjectMstrs;

	public Project() {
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

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public ClientMstr getClientMstr() {
		return this.clientMstr;
	}

	public void setClientMstr(ClientMstr clientMstr) {
		this.clientMstr = clientMstr;
	}

	public List<SubProjectMstr> getSubProjectMstrs() {
		return this.subProjectMstrs;
	}

	public void setSubProjectMstrs(List<SubProjectMstr> subProjectMstrs) {
		this.subProjectMstrs = subProjectMstrs;
	}

	public SubProjectMstr addSubProjectMstr(SubProjectMstr subProjectMstr) {
		getSubProjectMstrs().add(subProjectMstr);
		subProjectMstr.setProject(this);

		return subProjectMstr;
	}

	public SubProjectMstr removeSubProjectMstr(SubProjectMstr subProjectMstr) {
		getSubProjectMstrs().remove(subProjectMstr);
		subProjectMstr.setProject(null);

		return subProjectMstr;
	}

}