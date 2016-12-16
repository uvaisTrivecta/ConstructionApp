
package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the labour_mstr database table.
 * 
 */
@Entity
@Table(name="LABOUR_MSTR")
@NamedQueries ({
	@NamedQuery(name="LabourMstr.findAll", query="SELECT l FROM LabourMstr l"),
	@NamedQuery(name="LabourMstr.findByMobNo", query="SELECT l FROM LabourMstr l where l.communication.mobileNumber = :mobileNo"),
	@NamedQuery(name="LabourMstr.findByCriteria", query="SELECT l FROM LabourMstr l where"
			+ " lower(l.firstName) like lower(concat('%',:firstName,'%')) or "
			+ " lower(l.lastName) like lower(concat('%',:lastName,'%')) or "
			+ " lower(l.nickName) like lower(concat('%',:nickName,'%')) or "
			+ " l.communication.mobileNumber=:mobileNo")
})
public class LabourMstr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private String firstName;

	private int isDeleted;

	private String lastName;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String nickName;

	//bi-directional many-to-one association to AttendanceTracker
	@OneToMany(mappedBy="labourMstr")
	private List<AttendanceTracker> attendanceTrackers;

	//bi-directional many-to-one association to Communication
	@ManyToOne
	@JoinColumn(name="communicationId")
	private Communication communication;

	//bi-directional many-to-one association to LabourSkill
	@OneToMany(mappedBy="labourMstr")
	private List<LabourSkill> labourSkills;

	//bi-directional many-to-one association to LabourSubContractor
	@OneToMany(mappedBy="labourMstr")
	private List<LabourSubContractor> labourSubContractors;

	//bi-directional many-to-one association to SubProjectLabour
	@OneToMany(mappedBy="labourMstr")
	private List<SubProjectLabour> subProjectLabours;

	//bi-directional one-to-one association to BiometricInfo
	@OneToOne(mappedBy="labourMstr")
	private BiometricInfo biometricInfo;

	public LabourMstr() {
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<AttendanceTracker> getAttendanceTrackers() {
		return this.attendanceTrackers;
	}

	public void setAttendanceTrackers(List<AttendanceTracker> attendanceTrackers) {
		this.attendanceTrackers = attendanceTrackers;
	}

	public AttendanceTracker addAttendanceTracker(AttendanceTracker attendanceTracker) {
		getAttendanceTrackers().add(attendanceTracker);
		attendanceTracker.setLabourMstr(this);

		return attendanceTracker;
	}

	public AttendanceTracker removeAttendanceTracker(AttendanceTracker attendanceTracker) {
		getAttendanceTrackers().remove(attendanceTracker);
		attendanceTracker.setLabourMstr(null);

		return attendanceTracker;
	}

	public Communication getCommunication() {
		return this.communication;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}

	public List<LabourSkill> getLabourSkills() {
		return this.labourSkills;
	}

	public void setLabourSkills(List<LabourSkill> labourSkills) {
		this.labourSkills = labourSkills;
	}

	public LabourSkill addLabourSkill(LabourSkill labourSkill) {
		getLabourSkills().add(labourSkill);
		labourSkill.setLabourMstr(this);

		return labourSkill;
	}

	public LabourSkill removeLabourSkill(LabourSkill labourSkill) {
		getLabourSkills().remove(labourSkill);
		labourSkill.setLabourMstr(null);

		return labourSkill;
	}

	public List<LabourSubContractor> getLabourSubContractors() {
		return this.labourSubContractors;
	}

	public void setLabourSubContractors(List<LabourSubContractor> labourSubContractors) {
		this.labourSubContractors = labourSubContractors;
	}

	public LabourSubContractor addLabourSubContractor(LabourSubContractor labourSubContractor) {
		getLabourSubContractors().add(labourSubContractor);
		labourSubContractor.setLabourMstr(this);

		return labourSubContractor;
	}

	public LabourSubContractor removeLabourSubContractor(LabourSubContractor labourSubContractor) {
		getLabourSubContractors().remove(labourSubContractor);
		labourSubContractor.setLabourMstr(null);

		return labourSubContractor;
	}

	public List<SubProjectLabour> getSubProjectLabours() {
		return this.subProjectLabours;
	}

	public void setSubProjectLabours(List<SubProjectLabour> subProjectLabours) {
		this.subProjectLabours = subProjectLabours;
	}

	public SubProjectLabour addSubProjectLabour(SubProjectLabour subProjectLabour) {
		getSubProjectLabours().add(subProjectLabour);
		subProjectLabour.setLabourMstr(this);

		return subProjectLabour;
	}

	public SubProjectLabour removeSubProjectLabour(SubProjectLabour subProjectLabour) {
		getSubProjectLabours().remove(subProjectLabour);
		subProjectLabour.setLabourMstr(null);

		return subProjectLabour;
	}

	public BiometricInfo getBiometricInfo() {
		return this.biometricInfo;
	}

	public void setBiometricInfo(BiometricInfo biometricInfo) {
		this.biometricInfo = biometricInfo;
	}

}