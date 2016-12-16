package com.trivecta.attendencetracker.prime.faces.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.trivecta.attendencetracker.model.AttendanceTrackerVO;
import com.trivecta.attendencetracker.model.transformer.AttendenceTransformer;

@ManagedBean
@SessionScoped
public class AttendenceSearch{

	@ManagedProperty("#{attendenceTransformer}")
	private AttendenceTransformer attendenceTransformer;
	
	private AttendanceTrackerVO attendanceTrackerVO;
	
	private List<AttendanceTrackerVO> attendenceTrackerList;
	
	public void init(){		
		attendanceTrackerVO = new AttendanceTrackerVO();
		attendenceTrackerList = new ArrayList<AttendanceTrackerVO>();
	}

	public void search() {
		attendenceTrackerList = attendenceTransformer.getAttendanceReport(attendanceTrackerVO);
	}
	
	
	public AttendanceTrackerVO getAttendanceTrackerVO() {
		return attendanceTrackerVO;
	}

	public void setAttendanceTrackerVO(AttendanceTrackerVO attendanceTrackerVO) {
		this.attendanceTrackerVO = attendanceTrackerVO;
	}

	public List<AttendanceTrackerVO> getAttendenceTrackerList() {
		return attendenceTrackerList;
	}

	public void setAttendenceTrackerList(List<AttendanceTrackerVO> attendenceTrackerList) {
		this.attendenceTrackerList = attendenceTrackerList;
	}

	public AttendenceTransformer getAttendenceTransformer() {
		return attendenceTransformer;
	}

	public void setAttendenceTransformer(AttendenceTransformer attendenceTransformer) {
		this.attendenceTransformer = attendenceTransformer;
	}
	
}
