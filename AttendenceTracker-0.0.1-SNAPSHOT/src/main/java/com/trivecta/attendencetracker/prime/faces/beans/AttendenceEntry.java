package com.trivecta.attendencetracker.prime.faces.beans;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.trivecta.attendencetracker.model.AttendanceTrackerVO;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;
import com.trivecta.attendencetracker.model.transformer.AttendenceTransformer;

@ManagedBean
@SessionScoped
public class AttendenceEntry {

	@ManagedProperty("#{attendenceTransformer}")
	private AttendenceTransformer attendenceTransformer;
	
	private BigInteger mobNo;
	
	private String inOut;
	
	private List<AttendanceTrackerVO> attendenceTrackerVOList ;
	
	public void init(){
		mobNo = null;
		inOut = "0";
		attendenceTrackerVOList = new ArrayList<AttendanceTrackerVO>();
	}
	
	public void makeAttendence() {
		AttendanceTrackerVO attendenceTrackerVO = new AttendanceTrackerVO();
		
		attendenceTrackerVO.setMobNo(mobNo);
		attendenceTrackerVO.setStartDate(new Date());
		if("0".equalsIgnoreCase(inOut)) {			
			attendenceTrackerVO.setInTime(attendenceTrackerVO.getStartDate().getHours()+":"+attendenceTrackerVO.getStartDate().getMinutes());
		}
		else {
			attendenceTrackerVO.setEndDate(new Date());
			attendenceTrackerVO.setOutTime(attendenceTrackerVO.getEndDate().getHours()+":"+attendenceTrackerVO.getEndDate().getMinutes());
		}
		try {
			attendenceTransformer.saveAttendance(attendenceTrackerVO);
			searchAttendence();
		} catch (AttendenceTrackerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	public void searchAttendence() {
		attendenceTrackerVOList =new ArrayList<AttendanceTrackerVO>();
		attendenceTrackerVOList = attendenceTransformer.getAllAttendanceTrackerByMobAndDate(mobNo,new Date());
	}
	
	public AttendenceTransformer getAttendenceTransformer() {
		return attendenceTransformer;
	}

	public void setAttendenceTransformer(AttendenceTransformer attendenceTransformer) {
		this.attendenceTransformer = attendenceTransformer;
	}

	public BigInteger getMobNo() {
		return mobNo;
	}

	public void setMobNo(BigInteger mobNo) {
		this.mobNo = mobNo;
	}

	public List<AttendanceTrackerVO> getAttendenceTrackerVOList() {
		return attendenceTrackerVOList;
	}

	public void setAttendenceTrackerVOList(List<AttendanceTrackerVO> attendenceTrackerVOList) {
		this.attendenceTrackerVOList = attendenceTrackerVOList;
	}

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}
}
