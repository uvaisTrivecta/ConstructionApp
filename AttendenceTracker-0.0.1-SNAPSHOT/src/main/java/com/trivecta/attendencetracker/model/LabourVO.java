package com.trivecta.attendencetracker.model;

import java.math.BigInteger;
import java.util.Date;

public class LabourVO {
	
	private int id;
	
	private String firstName;
	private String lastName;
	private String nickName;
	private BigInteger mobNo;
	private Date dob;
	private String bloodGroup;
	private String pfNo;
	private String esiNo;
	private String knownHealthIssues;
		
	private int[] skills;	
	private int subContractId;
	
	private int communicationId;
	private AddressVO permanentAddress;
	private boolean currentSame;
	private AddressVO currentAddress;
	
	private String aadharNo;
	private String voterId;
	private String drivingLicencseNo;
	private String panNo;
	
	private int bankId;
	private String bankAccNo;
	private String ifscCode;
	private String bankName;
	private String branch;
	private int bankCityId;
	private int bankStateId;
	
	private String emergencyContactName;	
	private BigInteger emergencyContactNo;
	private boolean emergencySame;
	private AddressVO emergencyContactAddress;
	

	//File Upload
	private int biometricInfoId;
	private byte[] profileImage;
	private String profileName;
	
	private int projectId;
	private int subProjectId;
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDrivingLicencseNo() {
		return drivingLicencseNo;
	}
	public void setDrivingLicencseNo(String drivingLicencseNo) {
		this.drivingLicencseNo = drivingLicencseNo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigInteger getMobNo() {
		return mobNo;
	}
	public void setMobNo(BigInteger mobNo) {
		this.mobNo = mobNo;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getPfNo() {
		return pfNo;
	}
	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}
	public String getEsiNo() {
		return esiNo;
	}
	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}
	
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public void setEmergencyContactNo(BigInteger emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}
	public BigInteger getEmergencyContactNo() {
		return emergencyContactNo;
	}

	public int getSubContractId() {
		return subContractId;
	}
	public void setSubContractId(int subContractId) {
		this.subContractId = subContractId;
	}
	public int[] getSkills() {
		return skills;
	}
	public void setSkills(int[] skills) {
		this.skills = skills;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getKnownHealthIssues() {
		return knownHealthIssues;
	}
	public void setKnownHealthIssues(String knownHealthIssues) {
		this.knownHealthIssues = knownHealthIssues;
	}
	
	public void setPermanentAddress(AddressVO permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public void setCurrentAddress(AddressVO currentAddress) {
		this.currentAddress = currentAddress;
	}
	public AddressVO getPermanentAddress() {
		return permanentAddress;
	}
	public AddressVO getCurrentAddress() {
		return currentAddress;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public int getBankCityId() {
		return bankCityId;
	}
	public void setBankCityId(int bankCityId) {
		this.bankCityId = bankCityId;
	}
	public int getBankStateId() {
		return bankStateId;
	}
	public void setBankStateId(int bankStateId) {
		this.bankStateId = bankStateId;
	}
	
	public AddressVO getEmergencyContactAddress() {
		return emergencyContactAddress;
	}
	public void setEmergencyContactAddress(AddressVO emergencyContactAddress) {
		this.emergencyContactAddress = emergencyContactAddress;
	}
	public int getCommunicationId() {
		return communicationId;
	}
	public void setCommunicationId(int communicationId) {
		this.communicationId = communicationId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public boolean isCurrentSame() {
		return currentSame;
	}
	public void setCurrentSame(boolean currentSame) {
		this.currentSame = currentSame;
	}
	public boolean isEmergencySame() {
		return emergencySame;
	}
	public void setEmergencySame(boolean emergencySame) {
		this.emergencySame = emergencySame;
	}
	public int getBiometricInfoId() {
		return biometricInfoId;
	}
	public void setBiometricInfoId(int biometricInfoId) {
		this.biometricInfoId = biometricInfoId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getSubProjectId() {
		return subProjectId;
	}
	public void setSubProjectId(int subProjectId) {
		this.subProjectId = subProjectId;
	}
}