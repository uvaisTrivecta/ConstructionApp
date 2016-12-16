package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the labour_skills database table.
 * 
 */
@Entity
@Table(name="LABOUR_SKILLS")
@NamedQueries ({
	@NamedQuery(name="LabourSkill.findAll", query="SELECT l FROM LabourSkill l"),
	@NamedQuery(name="LabourSkill.DeleteByLabour", query="DELETE FROM LabourSkill l WHERE l.labourMstr.id = :labourMstrId")
})
public class LabourSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private int isDeleted;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	//bi-directional many-to-one association to LabourMstr
	@ManyToOne
	@JoinColumn(name="labourId")
	private LabourMstr labourMstr;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="skillId")
	private Skill skill;

	public LabourSkill() {
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

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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

	public LabourMstr getLabourMstr() {
		return this.labourMstr;
	}

	public void setLabourMstr(LabourMstr labourMstr) {
		this.labourMstr = labourMstr;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}