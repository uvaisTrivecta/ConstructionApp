package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the attendance_tracker database table.
 * 
 */
@Entity
@Table(name="ATTENDENCE_TRACKER")
@NamedQueries ({
	@NamedQuery(name="AttendanceTracker.findAll", query="SELECT a FROM AttendanceTracker a"),
	@NamedQuery(name="AttendanceTracker.findMobInDateOutDateNull", query="SELECT a FROM AttendanceTracker a where a.mobileNumber = :mobileNumber and DATE(a.startDate) = DATE(:startDate) and a.endDate IS NULL "),
	@NamedQuery(name="AttendanceTracker.findMobInDate", query="SELECT a FROM AttendanceTracker a where a.mobileNumber = :mobileNumber and DATE(a.startDate) = DATE(:startDate) "),
	@NamedQuery(name="AttendanceTracker.findByDate", query="SELECT a FROM AttendanceTracker a where a.startDate = :startDate")	
})
public class AttendanceTracker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private Time inTime;

	private Time outTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to LabourMstr
	@ManyToOne
	@JoinColumn(name="labourId" ,nullable=true)
	private LabourMstr labourMstr;
	
	private BigInteger mobileNumber;

	@Transient
	private String nickName;
	
	public AttendanceTracker() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getInTime() {
		return this.inTime;
	}

	public void setInTime(Time inTime) {
		this.inTime = inTime;
	}

	public Time getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Time outTime) {
		this.outTime = outTime;
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

	public BigInteger getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}