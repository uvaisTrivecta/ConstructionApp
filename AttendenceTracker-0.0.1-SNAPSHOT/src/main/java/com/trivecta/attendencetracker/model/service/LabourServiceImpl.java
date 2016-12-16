package com.trivecta.attendencetracker.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.trivecta.attendencetracker.model.LabourVO;
import com.trivecta.attendencetracker.model.dao.LabourDAO;
import com.trivecta.attendencetracker.model.entity.AttendanceTracker;
import com.trivecta.attendencetracker.model.entity.BiometricInfo;
import com.trivecta.attendencetracker.model.entity.LabourMstr;
import com.trivecta.attendencetracker.model.entity.Skill;
import com.trivecta.attendencetracker.model.entity.SubContractor;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;

@Service("labourService")
public class LabourServiceImpl implements LabourService{

	@Autowired
	LabourDAO labourDAO;

	@Transactional
	public LabourMstr saveLabour(LabourMstr labourMstr) throws AttendenceTrackerException{
		if(labourMstr.getId() == 0) {
			return labourDAO.createLabour(labourMstr);
		}
		else {
			return labourDAO.updateLabour(labourMstr);
		}		
	}
		
	@Transactional
	public List<LabourMstr> getAllLabourMstr() {
		return labourDAO.getAllLabourMstr();
	}
	
	@Transactional
	public LabourMstr getLabourById(int labourId){
		return labourDAO.getLabourById(labourId);
	}
		
	@Transactional
	public List<LabourMstr> getLabourDetails(LabourMstr labourMstr){
		return labourDAO.getLabourDetails(labourMstr);
	}
}