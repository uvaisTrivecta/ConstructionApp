package com.trivecta.attendencetracker.model.transformer;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trivecta.attendencetracker.model.AttendanceTrackerVO;
import com.trivecta.attendencetracker.model.entity.AttendanceTracker;
import com.trivecta.attendencetracker.model.entity.LabourMstr;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;
import com.trivecta.attendencetracker.model.service.AttendenceService;

@Component
public class AttendenceTransformer {

	@Autowired
	AttendenceService attendenceService;
	
	public AttendanceTrackerVO saveAttendance(AttendanceTrackerVO attendanceTrackerVO) throws AttendenceTrackerException {
		AttendanceTracker attendanceTracker = new AttendanceTracker();
		attendanceTracker.setMobileNumber(attendanceTrackerVO.getMobNo());
		/*LabourMstr labourMstr = new LabourMstr();
		labourMstr.setId(attendanceTrackerVO.getLabourId());
		attendanceTracker.setLabourMstr(labourMstr);*/
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		long ms;
		
		attendanceTracker.setStartDate(attendanceTrackerVO.getStartDate());		
		attendanceTracker.setEndDate(attendanceTrackerVO.getEndDate());
		try {
			if(attendanceTrackerVO.getInTime() != null && attendanceTrackerVO.getInTime().length() > 0) {
				ms = sdf.parse(attendanceTrackerVO.getInTime()).getTime();
				attendanceTracker.setInTime(new java.sql.Time(ms));
			}
			if(attendanceTrackerVO.getOutTime() != null && attendanceTrackerVO.getOutTime().length() > 0) {
				ms = sdf.parse(attendanceTrackerVO.getOutTime()).getTime();
				attendanceTracker.setOutTime(new java.sql.Time(ms));
			}
		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		attendanceTracker = attendenceService.saveAttendance(attendanceTracker);
		attendanceTrackerVO = setAttendanceTrackerVO(attendanceTracker);
		return attendanceTrackerVO;
	}
	
	public List<AttendanceTrackerVO> getAllAttendanceTrackerByMobAndDate(BigInteger mobNo,Date startDate) {
		AttendanceTracker attendanceTracker = new AttendanceTracker();
		attendanceTracker.setMobileNumber(mobNo);
		attendanceTracker.setStartDate(startDate);
		
		List<AttendanceTrackerVO> attendanceTrackerVOList = new ArrayList<AttendanceTrackerVO>();
		List<AttendanceTracker> attendanceTrackerList = attendenceService.getAllAttendanceTrackerByMobAndDate(attendanceTracker);
		
		for(AttendanceTracker attendanceTrackerResult : attendanceTrackerList) {
			attendanceTrackerVOList.add(setAttendanceTrackerVO(attendanceTrackerResult));
		}
		return attendanceTrackerVOList;
	}
	
	public List<AttendanceTrackerVO> getAttendanceReport(AttendanceTrackerVO attendanceTrackerVO) {
		AttendanceTracker attendanceTracker = new AttendanceTracker();
		attendanceTracker.setMobileNumber(attendanceTrackerVO.getMobNo());
		attendanceTracker.setStartDate(attendanceTrackerVO.getStartDate());
		attendanceTracker.setNickName(attendanceTrackerVO.getNickName());
		
		List<AttendanceTrackerVO> attendanceTrackerVOList = new ArrayList<AttendanceTrackerVO>();
		List<AttendanceTracker> attendanceTrackerList = attendenceService.getAttendanceReport(attendanceTracker);
		
		for(AttendanceTracker attendanceTrackerResult : attendanceTrackerList) {
			attendanceTrackerVOList.add(setAttendanceTrackerVO(attendanceTrackerResult));
		}
		return attendanceTrackerVOList;
	}
	
	private AttendanceTrackerVO setAttendanceTrackerVO(AttendanceTracker attendanceTracker ) {
		AttendanceTrackerVO attendanceTrackerVO = new AttendanceTrackerVO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy ");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		
		attendanceTrackerVO.setStartDateStr(sdf.format(attendanceTracker.getStartDate()));
		
		if(attendanceTracker.getEndDate() != null) {
			attendanceTrackerVO.setEndDateStr(sdf.format(attendanceTracker.getEndDate()));
		}
			
		attendanceTrackerVO.setId(attendanceTracker.getId());
		attendanceTrackerVO.setMobNo(attendanceTracker.getMobileNumber());
		if(attendanceTracker.getInTime() != null) 
			attendanceTrackerVO.setInTime(sdf1.format(attendanceTracker.getInTime()));
		if(attendanceTracker.getOutTime() != null) 
			attendanceTrackerVO.setOutTime(sdf1.format(attendanceTracker.getOutTime()));
		if(attendanceTracker.getLabourMstr() != null) {
			attendanceTrackerVO.setLabourId(attendanceTracker.getLabourMstr().getId());
			/*String name = attendanceTracker.getLabourMstr().getFirstName();
			if(attendanceTracker.getLabourMstr().getLastName() !=null &&
					attendanceTracker.getLabourMstr().getLastName().length() >0){ 
				name = name +" "+attendanceTracker.getLabourMstr().getLastName();
			}*/
			attendanceTrackerVO.setNickName(attendanceTracker.getLabourMstr().getNickName());
		}
		
		return attendanceTrackerVO;
	}
	
	
	public int[] getAttendenceCountBySubProjectId(int subProjectId) {
		int[] attendenceCount = attendenceService.getAttendenceCountBySubProjectId(subProjectId);
		return attendenceCount;
	}
}
