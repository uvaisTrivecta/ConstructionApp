package com.trivecta.attendencetracker.model;

import java.math.BigInteger;

public class LabourSearchVO {

	private int id;
	private String name;
	private String nickName;
	private BigInteger mobNo;
	private String address;
	private String emergencyContact;
	private BigInteger emergencyContactNo;
	
	private String bloodGroup;
	private int cityId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public BigInteger getMobNo() {
		return mobNo;
	}
	public void setMobNo(BigInteger mobNo) {
		this.mobNo = mobNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public BigInteger getEmergencyContactNo() {
		return emergencyContactNo;
	}
	public void setEmergencyContactNo(BigInteger emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
}
