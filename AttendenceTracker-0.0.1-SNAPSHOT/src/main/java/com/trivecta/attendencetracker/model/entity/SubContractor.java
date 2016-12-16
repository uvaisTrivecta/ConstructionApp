package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sub_contractors database table.
 * 
 */
@Entity
@Table(name="SUB_CONTRACTORS")
@NamedQuery(name="SubContractor.findAll", query="SELECT s FROM SubContractor s")
public class SubContractor implements Serializable {
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

	//bi-directional many-to-one association to LabourSubContractor
	@OneToMany(mappedBy="subContractor")
	private List<LabourSubContractor> labourSubContractors;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="addressId")
	private Address address;

	public SubContractor() {
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

	public List<LabourSubContractor> getLabourSubContractors() {
		return this.labourSubContractors;
	}

	public void setLabourSubContractors(List<LabourSubContractor> labourSubContractors) {
		this.labourSubContractors = labourSubContractors;
	}

	public LabourSubContractor addLabourSubContractor(LabourSubContractor labourSubContractor) {
		getLabourSubContractors().add(labourSubContractor);
		labourSubContractor.setSubContractor(this);

		return labourSubContractor;
	}

	public LabourSubContractor removeLabourSubContractor(LabourSubContractor labourSubContractor) {
		getLabourSubContractors().remove(labourSubContractor);
		labourSubContractor.setSubContractor(null);

		return labourSubContractor;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}