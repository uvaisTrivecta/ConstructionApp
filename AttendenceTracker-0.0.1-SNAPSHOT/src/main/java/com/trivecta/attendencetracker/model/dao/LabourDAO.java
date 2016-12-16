package com.trivecta.attendencetracker.model.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.trivecta.attendencetracker.model.LabourVO;
import com.trivecta.attendencetracker.model.entity.AttendanceTracker;
import com.trivecta.attendencetracker.model.entity.BiometricInfo;
import com.trivecta.attendencetracker.model.entity.LabourMstr;
import com.trivecta.attendencetracker.model.entity.Skill;
import com.trivecta.attendencetracker.model.entity.SubContractor;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;

public interface LabourDAO {
	
	public List<LabourMstr> getAllLabourMstr();
	public LabourMstr createLabour(LabourMstr labourMstr) throws AttendenceTrackerException ;
	
	public List<LabourMstr> getLabourDetails(LabourMstr labourMstr);
	public BiometricInfo saveLabourBiometricInfo(MultipartFile file,int labourId);
	public LabourMstr getLabourById(int labourId);
	public LabourMstr getLabourByMobileNo(BigInteger mobileNo) ;
	public LabourMstr updateLabour(LabourMstr labourMstr);
}
