package com.trivecta.attendencetracker.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivecta.attendencetracker.model.dao.AttendenceDAO;
import com.trivecta.attendencetracker.model.entity.AttendanceTracker;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;

@Service("attendenceService")
public class AttendenceServiceImpl implements AttendenceService {

	@Autowired
	AttendenceDAO attendenceDAO;	

	@Transactional
	public AttendanceTracker saveAttendance(AttendanceTracker attendanceTracker) throws AttendenceTrackerException{
		return attendenceDAO.saveAttendance(attendanceTracker);
	}
	
	@Transactional
	public List<AttendanceTracker> getAttendanceReport(AttendanceTracker attendenceTracker) {
		return attendenceDAO.getAttendanceReport(attendenceTracker);
	}
	
	@Transactional
	public List<AttendanceTracker> getAllAttendanceTrackerByDate(Date startDate) {
		return attendenceDAO.getAllAttendanceTrackerByDate(startDate);
	}

	@Transactional
	public List<AttendanceTracker> getAllAttendanceTrackerByMobAndDate(AttendanceTracker attendanceTracker) {
		return attendenceDAO.getAllAttendanceTrackerByMobAndDate(attendanceTracker);
	}

	@Transactional
	public int[] getAttendenceCountBySubProjectId(int subProjectId) {
		return attendenceDAO.getAttendenceCountBySubProjectId(subProjectId);
	}	
}