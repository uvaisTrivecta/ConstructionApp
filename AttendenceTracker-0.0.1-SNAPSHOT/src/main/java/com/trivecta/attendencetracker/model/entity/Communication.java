package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the communication database table.
 * 
 */
@Entity
@Table(name="COMMUNICATION")
@NamedQuery(name="Communication.findAll", query="SELECT c FROM Communication c")
public class Communication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String aadharNo;

	private String bloodGroup;

	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	private String drivingLicenseNumber;

	private String emergencyContactName;

	private BigInteger emergencyContactNumber;

	private String esiNumber;

	private int iscurrentSame;

	private int isEmergencySame;

	private String knownHealthIssue;

	private BigInteger mobileNumber;

	private int modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String panNumber;

	private String pfNumber;

	private String voterId;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="permanentAddressId")
	private Address address1;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="currentAddressId")
	private Address address2;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="emergencyAddressId")
	private Address address3;

	//bi-directional many-to-one association to Bank
	@ManyToOne
	@JoinColumn(name="bankId")
	private Bank bank;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="communication")
	private List<Employee> employees;

	//bi-directional many-to-one association to LabourMstr
	@OneToMany(mappedBy="communication")
	private List<LabourMstr> labourMstrs;

	public Communication() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAadharNo() {
		return this.aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDrivingLicenseNumber() {
		return this.drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getEmergencyContactName() {
		return this.emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public BigInteger getEmergencyContactNumber() {
		return this.emergencyContactNumber;
	}

	public void setEmergencyContactNumber(BigInteger emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getEsiNumber() {
		return this.esiNumber;
	}

	public void setEsiNumber(String esiNumber) {
		this.esiNumber = esiNumber;
	}

	public int getIscurrentSame() {
		return this.iscurrentSame;
	}

	public void setIscurrentSame(int iscurrentSame) {
		this.iscurrentSame = iscurrentSame;
	}

	public int getIsEmergencySame() {
		return this.isEmergencySame;
	}

	public void setIsEmergencySame(int isEmergencySame) {
		this.isEmergencySame = isEmergencySame;
	}

	public String getKnownHealthIssue() {
		return this.knownHealthIssue;
	}

	public void setKnownHealthIssue(String knownHealthIssue) {
		this.knownHealthIssue = knownHealthIssue;
	}

	public BigInteger getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public String getPanNumber() {
		return this.panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPfNumber() {
		return this.pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public String getVoterId() {
		return this.voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public Address getAddress1() {
		return this.address1;
	}

	public void setAddress1(Address address1) {
		this.address1 = address1;
	}

	public Address getAddress2() {
		return this.address2;
	}

	public void setAddress2(Address address2) {
		this.address2 = address2;
	}

	public Address getAddress3() {
		return this.address3;
	}

	public void setAddress3(Address address3) {
		this.address3 = address3;
	}

	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setCommunication(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setCommunication(null);

		return employee;
	}

	public List<LabourMstr> getLabourMstrs() {
		return this.labourMstrs;
	}

	public void setLabourMstrs(List<LabourMstr> labourMstrs) {
		this.labourMstrs = labourMstrs;
	}

	public LabourMstr addLabourMstr(LabourMstr labourMstr) {
		getLabourMstrs().add(labourMstr);
		labourMstr.setCommunication(this);

		return labourMstr;
	}

	public LabourMstr removeLabourMstr(LabourMstr labourMstr) {
		getLabourMstrs().remove(labourMstr);
		labourMstr.setCommunication(null);

		return labourMstr;
	}

}