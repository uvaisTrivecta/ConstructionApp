package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the skills database table.
 * 
 */
@Entity
@Table(name="SKILLS")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
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

	private String skill;

	//bi-directional many-to-one association to LabourSkill
	@OneToMany(mappedBy="skill")
	private List<LabourSkill> labourSkills;

	public Skill() {
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

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<LabourSkill> getLabourSkills() {
		return this.labourSkills;
	}

	public void setLabourSkills(List<LabourSkill> labourSkills) {
		this.labourSkills = labourSkills;
	}

	public LabourSkill addLabourSkill(LabourSkill labourSkill) {
		getLabourSkills().add(labourSkill);
		labourSkill.setSkill(this);

		return labourSkill;
	}

	public LabourSkill removeLabourSkill(LabourSkill labourSkill) {
		getLabourSkills().remove(labourSkill);
		labourSkill.setSkill(null);

		return labourSkill;
	}

}