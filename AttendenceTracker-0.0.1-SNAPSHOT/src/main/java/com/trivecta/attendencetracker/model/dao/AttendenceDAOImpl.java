package com.trivecta.attendencetracker.model.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trivecta.attendencetracker.constants.AttendenceTrackerConstants.ErrorMessages;
import com.trivecta.attendencetracker.model.entity.AttendanceTracker;
import com.trivecta.attendencetracker.model.entity.LabourMstr;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;

@Repository
public class AttendenceDAOImpl implements AttendenceDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private LabourDAO labourDAO;
	
	public AttendanceTracker saveAttendance(AttendanceTracker attendanceTracker) throws AttendenceTrackerException{
		Session session = this.sessionFactory.getCurrentSession();
		AttendanceTracker origAttendanceTracker = new AttendanceTracker();		
		try {
			origAttendanceTracker = (AttendanceTracker) session.createNamedQuery("AttendanceTracker.findMobInDateOutDateNull").
					setParameter("mobileNumber", attendanceTracker.getMobileNumber()).
					setParameter("startDate", attendanceTracker.getStartDate()).getSingleResult();
		}
		catch(NoResultException ne) {
			//No Daata present
		}
		
		if(origAttendanceTracker.getId() == 0) {
			LabourMstr labourMstr = labourDAO.getLabourByMobileNo(attendanceTracker.getMobileNumber());
			if(labourMstr != null) {
				attendanceTracker.setLabourMstr(labourMstr);
				session.save(attendanceTracker);
				return attendanceTracker;
			}
			else {
				throw new AttendenceTrackerException(ErrorMessages.MOB_NOT_EXITS);
			}
			
		}
		else {
			origAttendanceTracker.setEndDate(attendanceTracker.getEndDate());
			origAttendanceTracker.setOutTime(attendanceTracker.getOutTime());			
			session.merge(origAttendanceTracker);
			return origAttendanceTracker;					
		}
	}
	
	public List<AttendanceTracker> getAllAttendanceTrackerByMobAndDate(AttendanceTracker attendanceTracker) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("AttendanceTracker.findMobInDate").
				setParameter("mobileNumber", attendanceTracker.getMobileNumber()).
				setParameter("startDate", attendanceTracker.getStartDate());
		List<AttendanceTracker> attendanceList = query.getResultList();
		
		for(AttendanceTracker attendanceTrackerData:attendanceList) {
			attendanceTrackerData.getLabourMstr();
		}
		return attendanceList;
	}

	public List<AttendanceTracker> getAttendanceReport(AttendanceTracker attendenceTracker) {		
		Session session = this.sessionFactory.getCurrentSession();
		StringBuffer queryStr = new StringBuffer("SELECT a.* FROM ATTENDENCE_TRACKER a , LABOUR_MSTR l WHERE a.labourId = l.id");
				
		if(attendenceTracker.getMobileNumber() != null) {
			
			queryStr.append(" AND a.mobileNumber = ").append(attendenceTracker.getMobileNumber());			
		}
		
		if(attendenceTracker.getNickName() != null && attendenceTracker.getNickName().length() > 0) {			
			queryStr.append(" AND LOWER(l.nickName) = LOWER('%").append(attendenceTracker.getNickName()).append("%')");			
		}
		
		if(attendenceTracker.getStartDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			queryStr.append(" AND DATE(a.startDate) = DATE('").append(sdf.format(attendenceTracker.getStartDate())).append("')");
		}
		
		Query query = session.createNativeQuery(queryStr.toString(), AttendanceTracker.class);
		List<AttendanceTracker> attendanceList = query.getResultList();
		for(AttendanceTracker attendanceTracker:attendanceList) {
			attendanceTracker.getLabourMstr();
		}
		return attendanceList;
	}
	
	public List<AttendanceTracker> getAllAttendanceTrackerByDate(Date startDate) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("AttendanceTracker.findByDate").setParameter("startDate", startDate);
		List<AttendanceTracker> attendanceList = query.getResultList();
		for(AttendanceTracker attendanceTracker:attendanceList) {
			attendanceTracker.getLabourMstr();
		}
		return attendanceList;
	}
	
	public int[] getAttendenceCountBySubProjectId(int subProjectId) {
		int[] attendenceCount = new int[3];
		Session session = this.sessionFactory.getCurrentSession();
		String qryStr = "SELECT count(distinct s.labourId) FROM SUB_PROJECT_LABOUR s where s.subProjectId = :subProjectId";
		//Total Count in Project
		Query query = session.createNativeQuery(qryStr).setParameter("subProjectId",subProjectId);
		Object resultObj = query.getSingleResult();
		BigInteger count = (BigInteger)resultObj;
		attendenceCount[0] = count.intValue();
							
		//Total In Count		
		 count =  new BigInteger("0");
		StringBuffer queryStr =	new StringBuffer("select COUNT(DISTINCT a.labourId) FROM ATTENDENCE_TRACKER a WHERE ")
				.append("a.labourId in (select s.labourId from SUB_PROJECT_LABOUR s where s.subProjectId =:subProjectId) ")
				.append(" and DATE(a.startDate) = DATE(NOW()) AND a.outTime is NULL ");
		
		Object resultObj1 = session.createNativeQuery(queryStr.toString()).setParameter("subProjectId",subProjectId).getSingleResult();
		count = (BigInteger)resultObj1;
		attendenceCount[1] = count.intValue();		
		attendenceCount[2] = 0;
		return attendenceCount;		
	}
}
