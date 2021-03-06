package com.trivecta.attendencetracker.model.service;

import java.util.Date;
import java.util.List;

import com.trivecta.attendencetracker.model.entity.AttendanceTracker;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;

public interface AttendenceService {

	public AttendanceTracker saveAttendance(AttendanceTracker attendanceTracker) throws AttendenceTrackerException ;
	public List<AttendanceTracker> getAttendanceReport(AttendanceTracker attendenceTracker);
	public List<AttendanceTracker> getAllAttendanceTrackerByDate(Date startDate);
	public List<AttendanceTracker> getAllAttendanceTrackerByMobAndDate(AttendanceTracker attendanceTracker) ;
	public int[] getAttendenceCountBySubProjectId(int subProjectId);
}
