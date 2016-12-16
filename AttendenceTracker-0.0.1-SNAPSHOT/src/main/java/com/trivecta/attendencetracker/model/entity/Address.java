package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="ADDRESS")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String address1;

	private String address2;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private BigInteger postalCode;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="cityId")
	private City city;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="stateId")
	private State state;

	//bi-directional many-to-one association to ClientMstr
	@OneToMany(mappedBy="address")
	private List<ClientMstr> clientMstrs;

	//bi-directional many-to-one association to Communication
	@OneToMany(mappedBy="address1")
	private List<Communication> communications1;

	//bi-directional many-to-one association to Communication
	@OneToMany(mappedBy="address2")
	private List<Communication> communications2;

	//bi-directional many-to-one association to Communication
	@OneToMany(mappedBy="address3")
	private List<Communication> communications3;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="address")
	private List<Project> projects;

	//bi-directional many-to-one association to SubContractor
	@OneToMany(mappedBy="address")
	private List<SubContractor> subContractors;

	public Address() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public BigInteger getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(BigInteger postalCode) {
		this.postalCode = postalCode;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<ClientMstr> getClientMstrs() {
		return this.clientMstrs;
	}

	public void setClientMstrs(List<ClientMstr> clientMstrs) {
		this.clientMstrs = clientMstrs;
	}

	public ClientMstr addClientMstr(ClientMstr clientMstr) {
		getClientMstrs().add(clientMstr);
		clientMstr.setAddress(this);

		return clientMstr;
	}

	public ClientMstr removeClientMstr(ClientMstr clientMstr) {
		getClientMstrs().remove(clientMstr);
		clientMstr.setAddress(null);

		return clientMstr;
	}

	public List<Communication> getCommunications1() {
		return this.communications1;
	}

	public void setCommunications1(List<Communication> communications1) {
		this.communications1 = communications1;
	}

	public Communication addCommunications1(Communication communications1) {
		getCommunications1().add(communications1);
		communications1.setAddress1(this);

		return communications1;
	}

	public Communication removeCommunications1(Communication communications1) {
		getCommunications1().remove(communications1);
		communications1.setAddress1(null);

		return communications1;
	}

	public List<Communication> getCommunications2() {
		return this.communications2;
	}

	public void setCommunications2(List<Communication> communications2) {
		this.communications2 = communications2;
	}

	public Communication addCommunications2(Communication communications2) {
		getCommunications2().add(communications2);
		communications2.setAddress2(this);

		return communications2;
	}

	public Communication removeCommunications2(Communication communications2) {
		getCommunications2().remove(communications2);
		communications2.setAddress2(null);

		return communications2;
	}

	public List<Communication> getCommunications3() {
		return this.communications3;
	}

	public void setCommunications3(List<Communication> communications3) {
		this.communications3 = communications3;
	}

	public Communication addCommunications3(Communication communications3) {
		getCommunications3().add(communications3);
		communications3.setAddress3(this);

		return communications3;
	}

	public Communication removeCommunications3(Communication communications3) {
		getCommunications3().remove(communications3);
		communications3.setAddress3(null);

		return communications3;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setAddress(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setAddress(null);

		return project;
	}

	public List<SubContractor> getSubContractors() {
		return this.subContractors;
	}

	public void setSubContractors(List<SubContractor> subContractors) {
		this.subContractors = subContractors;
	}

	public SubContractor addSubContractor(SubContractor subContractor) {
		getSubContractors().add(subContractor);
		subContractor.setAddress(this);

		return subContractor;
	}

	public SubContractor removeSubContractor(SubContractor subContractor) {
		getSubContractors().remove(subContractor);
		subContractor.setAddress(null);

		return subContractor;
	}

}