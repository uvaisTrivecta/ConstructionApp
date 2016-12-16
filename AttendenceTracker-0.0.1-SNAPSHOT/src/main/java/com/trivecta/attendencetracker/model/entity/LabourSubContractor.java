package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the labour_sub_contractor database table.
 * 
 */
@Entity
@Table(name="LABOUR_SUB_CONTRACTOR")
@NamedQueries ({
	@NamedQuery(name="LabourSubContractor.findAll", query="SELECT l FROM LabourSubContractor l"),
	@NamedQuery(name="LabourSubContractor.DeleteByLabour", query="DELETE FROM LabourSubContractor l WHERE l.labourMstr.id = :labourMstrId")
})
public class LabourSubContractor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to LabourMstr
	@ManyToOne
	@JoinColumn(name="labourId")
	private LabourMstr labourMstr;

	//bi-directional many-to-one association to SubContractor
	@ManyToOne
	@JoinColumn(name="subContractorId")
	private SubContractor subContractor;

	public LabourSubContractor() {
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

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public LabourMstr getLabourMstr() {
		return this.labourMstr;
	}

	public void setLabourMstr(LabourMstr labourMstr) {
		this.labourMstr = labourMstr;
	}

	public SubContractor getSubContractor() {
		return this.subContractor;
	}

	public void setSubContractor(SubContractor subContractor) {
		this.subContractor = subContractor;
	}

}