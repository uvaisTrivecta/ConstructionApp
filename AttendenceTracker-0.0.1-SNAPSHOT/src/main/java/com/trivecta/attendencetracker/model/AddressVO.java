package com.trivecta.attendencetracker.model;

import java.math.BigInteger;

public class AddressVO {

	private int addressId;
	private String addressLine1;
	private String addressLine2;
	private int cityId;
	private int stateId;
	private BigInteger postalCode;
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public BigInteger getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(BigInteger postalCode) {
		this.postalCode = postalCode;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
}
