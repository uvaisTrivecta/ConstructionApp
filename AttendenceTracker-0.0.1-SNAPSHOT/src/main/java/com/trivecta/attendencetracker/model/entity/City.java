package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="CITY")
@NamedQueries({
	@NamedQuery(name="City.findAll", query="SELECT c FROM City c"),
	@NamedQuery(name="City.findByName", query="SELECT c FROM City c where c.name = :name")
})
public class City implements Serializable {
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

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="city")
	private List<Address> addresses;

	//bi-directional many-to-one association to Bank
	@OneToMany(mappedBy="city")
	private List<Bank> banks;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="stateId")
	private State state;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="city")
	private List<Project> projects;

	public City() {
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

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCity(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCity(null);

		return address;
	}

	public List<Bank> getBanks() {
		return this.banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public Bank addBank(Bank bank) {
		getBanks().add(bank);
		bank.setCity(this);

		return bank;
	}

	public Bank removeBank(Bank bank) {
		getBanks().remove(bank);
		bank.setCity(null);

		return bank;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setCity(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setCity(null);

		return project;
	}

}