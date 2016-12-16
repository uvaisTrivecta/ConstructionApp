package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bank database table.
 * 
 */
@Entity
@Table(name="BANK")
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String accNumber;

	private String bankBranch;

	private String bankName;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private String ifscCode;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="cityId")
	private City city;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="stateId")
	private State state;

	//bi-directional many-to-one association to Communication
	@OneToMany(mappedBy="bank")
	private List<Communication> communications;

	public Bank() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccNumber() {
		return this.accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getBankBranch() {
		return this.bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
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

	public List<Communication> getCommunications() {
		return this.communications;
	}

	public void setCommunications(List<Communication> communications) {
		this.communications = communications;
	}

	public Communication addCommunication(Communication communication) {
		getCommunications().add(communication);
		communication.setBank(this);

		return communication;
	}

	public Communication removeCommunication(Communication communication) {
		getCommunications().remove(communication);
		communication.setBank(null);

		return communication;
	}

}