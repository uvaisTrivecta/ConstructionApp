package com.trivecta.attendencetracker.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

public class AttendanceTrackerVO{
	
	private String nickName;
	
	private BigInteger mobNo;
	
	private Date startDate;
	
	private Date endDate;
	
	private int id;

	private String inTime;

	private String outTime;	
	
	private int labourId;
	
	private String projectName;
	
	private String startDateStr;
	
	private String endDateStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public int getLabourId() {
		return labourId;
	}

	public void setLabourId(int labourId) {
		this.labourId = labourId;
	}

	public BigInteger getMobNo() {
		return mobNo;
	}

	public void setMobNo(BigInteger mobNo) {
		this.mobNo = mobNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
