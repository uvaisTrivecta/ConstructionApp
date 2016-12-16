package com.trivecta.attendencetracker.model.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.trivecta.attendencetracker.constants.AttendenceTrackerConstants.ErrorMessages;
import com.trivecta.attendencetracker.model.entity.Address;
import com.trivecta.attendencetracker.model.entity.AttendanceTracker;
import com.trivecta.attendencetracker.model.entity.Bank;
import com.trivecta.attendencetracker.model.entity.BiometricInfo;
import com.trivecta.attendencetracker.model.entity.City;
import com.trivecta.attendencetracker.model.entity.Communication;
import com.trivecta.attendencetracker.model.entity.LabourMstr;
import com.trivecta.attendencetracker.model.entity.LabourSkill;
import com.trivecta.attendencetracker.model.entity.LabourSubContractor;
import com.trivecta.attendencetracker.model.entity.Skill;
import com.trivecta.attendencetracker.model.entity.State;
import com.trivecta.attendencetracker.model.entity.SubContractor;
import com.trivecta.attendencetracker.model.entity.SubProjectLabour;
import com.trivecta.attendencetracker.model.entity.SubProjectMstr;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;

@Repository
public class LabourDAOImpl implements LabourDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LabourMstr> getAllLabourMstr() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("LabourMstr.findAll");
		List<LabourMstr>  labourMstrList = query.getResultList();
		return labourMstrList;
	}
	
	public List<LabourMstr> getLabourDetails(LabourMstr labourMstr){
		Session session = this.sessionFactory.getCurrentSession();
		boolean isAboveExists = false;
		
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT DISTINCT l.* FROM LABOUR_MSTR l ,COMMUNICATION c, ADDRESS a , CITY ci WHERE ");
		queryStr.append(" l.communicationId = c.id and c.permanentAddressId = a.id and a.cityId = ci.id and ");
		if(labourMstr.getNickName() != null && labourMstr.getNickName().length() >0) {
			queryStr.append(" upper(l.nickName) = upper('%").append(labourMstr.getNickName()).append("%'");
			isAboveExists = true;
		}
		if(labourMstr.getCommunication() != null) {
			Communication communication = labourMstr.getCommunication();
			if(isAboveExists) {
				queryStr.append(" and ");
				isAboveExists = false;
			}
			if(communication.getMobileNumber() != null && communication.getMobileNumber().intValue() != 0){
				queryStr.append(" c.mobileNumber = ").append(communication.getMobileNumber());
				isAboveExists = true;
			}
			if(communication.getBloodGroup()!= null && communication.getBloodGroup().length()>0){
				if(isAboveExists) {
					queryStr.append(" and ");
				}
				queryStr.append(" c.bloodGroup = '").append(communication.getBloodGroup()).append("'");
				isAboveExists = true;
			}
			if(communication.getAddress1()!= null && communication.getAddress1().getCity() != null &&
					communication.getAddress1().getCity().getId() != 0){
				if(isAboveExists) {
					queryStr.append(" and ");
				}
				queryStr.append(" ci.id = ").
				append(communication.getAddress1().getCity().getId());
			}
		}
				
		Query query = session.createNativeQuery(queryStr.toString()).addEntity(LabourMstr.class);		
		
		List<LabourMstr> labourMstrList = query.getResultList();
		for(LabourMstr labour:labourMstrList) {
			labour.getBiometricInfo();
			labour.getCommunication();
			if(labour.getLabourSkills() != null) {
				labour.getLabourSkills().size();
			}
			if(labour.getLabourSubContractors() != null) {
				labour.getLabourSubContractors().size();
			}	
			if(labour.getSubProjectLabours() != null) {
				labour.getSubProjectLabours().size();
			}
		}
		return labourMstrList;
	}
	
	public LabourMstr getLabourById(int labourId) {
		Session session = this.sessionFactory.getCurrentSession();
		LabourMstr labourMstr = null;		
		try{
			labourMstr = session.find(LabourMstr.class, labourId);		
		}
		catch(NoResultException e) {
			
		}
		if(labourMstr!= null) {
			labourMstr.getBiometricInfo();
			labourMstr.getCommunication();
			labourMstr.getCommunication().getAddress1();
			labourMstr.getCommunication().getAddress2();
			labourMstr.getCommunication().getAddress3();
			if(labourMstr.getLabourSkills() != null) {
				labourMstr.getLabourSkills().size();
			}
			if(labourMstr.getLabourSubContractors() != null) {
				labourMstr.getLabourSubContractors().size();
			}	
			if(labourMstr.getSubProjectLabours() != null) {
				labourMstr.getSubProjectLabours().size();
			}
		}
		return labourMstr;
	}
	
	public LabourMstr getLabourByMobileNo(BigInteger mobileNo){
		Session session = this.sessionFactory.getCurrentSession();
		LabourMstr labourMstr = null;		
		try{
			labourMstr = (LabourMstr) session.createNamedQuery("LabourMstr.findByMobNo").setParameter("mobileNo", mobileNo).getSingleResult();	
		}
		catch(NoResultException e) {
			
		}
		if(labourMstr!= null) {
			labourMstr.getBiometricInfo();
			labourMstr.getCommunication();
			if(labourMstr.getLabourSkills() != null) {
				labourMstr.getLabourSkills().size();
			}
			if(labourMstr.getLabourSubContractors() != null) {
				labourMstr.getLabourSubContractors().size();
			}	
			if(labourMstr.getSubProjectLabours() != null) {
				labourMstr.getSubProjectLabours().size();
			}
		}
		return labourMstr;
	}
	
	
	public LabourMstr createLabour(LabourMstr labourMstr) throws AttendenceTrackerException {
		Session session = this.sessionFactory.getCurrentSession();
		
		LabourMstr origLabourMstr = getLabourByMobileNo(labourMstr.getCommunication().getMobileNumber());
		if(origLabourMstr == null) {		
			origLabourMstr = labourMstr;
			Communication communication = saveCommunication(labourMstr);
			labourMstr.setCommunication(communication);
			labourMstr.setCreationDate(new Date());
			session.save(labourMstr);
			saveCommonLabourDetails(labourMstr,origLabourMstr);
		}
		else {
			throw new AttendenceTrackerException(ErrorMessages.MOB_EXISTS);
		}
		
		labourMstr.getCommunication();
		labourMstr.getBiometricInfo();
		if(labourMstr.getLabourSkills() != null) {
			labourMstr.getLabourSkills().size();
		}
		if(labourMstr.getLabourSubContractors() != null) {
			labourMstr.getLabourSubContractors().size();
		}		
		return labourMstr;
	}
	
	public LabourMstr updateLabour(LabourMstr labourMstr) {
		Session session = this.sessionFactory.getCurrentSession();
		
		LabourMstr origLabourMstr = session.find(LabourMstr.class, labourMstr.getId());
		
		if(origLabourMstr != null) {
			Communication communication = saveCommunication(labourMstr);
			origLabourMstr.setCommunication(communication);
			
			origLabourMstr.setFirstName(labourMstr.getFirstName());
			origLabourMstr.setLastName(labourMstr.getLastName());
			origLabourMstr.setModifiedDate(new Date());
			origLabourMstr = (LabourMstr) session.merge(origLabourMstr);
			deleteCommonLabourDetails(origLabourMstr.getId());
			saveCommonLabourDetails(origLabourMstr,labourMstr);
		}
		
		origLabourMstr.getCommunication();
		origLabourMstr.getBiometricInfo();
		if(origLabourMstr.getLabourSkills() != null) {
			origLabourMstr.getLabourSkills().size();
		}
		if(origLabourMstr.getLabourSubContractors() != null) {
			origLabourMstr.getLabourSubContractors().size();
		}
		if(origLabourMstr.getSubProjectLabours() != null) {
			origLabourMstr.getSubProjectLabours().size();
		}
		return origLabourMstr;
	}
	
	private void saveCommonLabourDetails(LabourMstr labourMstrToPersist,LabourMstr origLabourMstr) {
		Session session = this.sessionFactory.getCurrentSession();
		List<LabourSkill> labourSkills = origLabourMstr.getLabourSkills();
		if(origLabourMstr.getLabourSubContractors() != null && origLabourMstr.getLabourSubContractors().size() > 0) {			
			for(LabourSubContractor labourSubContractor : origLabourMstr.getLabourSubContractors()) {
				if(labourSubContractor.getId() == 0) {
					SubContractor subContractor = null;
					try {
						subContractor = session.find(SubContractor.class, labourSubContractor.getSubContractor().getId());
					}
					catch(NoResultException e){
							
					}
					if(subContractor != null) {
						labourSubContractor.setSubContractor(subContractor);
						labourSubContractor.setLabourMstr(labourMstrToPersist);
						labourSubContractor.setCreationDate(labourMstrToPersist.getCreationDate());
						session.save(labourSubContractor);
					}		
				}				
			}
		}

		if (labourSkills != null && labourSkills.size() > 0) {
			for (LabourSkill labourSkill : labourSkills) {
				if(labourSkill.getId() == 0) {
					Skill skill = null;
					try {
						skill = session.find(Skill.class, labourSkill.getSkill().getId());
					} catch (NoResultException e) {

					}
					if (skill != null) {
						labourSkill.setSkill(skill);
						labourSkill.setLabourMstr(labourMstrToPersist);
						labourSkill.setCreationDate(labourMstrToPersist.getCreationDate());
						session.save(labourSkill);
					}
				}				
			}
		}
		
		if(origLabourMstr.getSubProjectLabours() != null && origLabourMstr.getSubProjectLabours().size() > 0) {
			for(SubProjectLabour subProjectLabour : origLabourMstr.getSubProjectLabours()) {
				if(subProjectLabour.getId() == 0) {
					SubProjectMstr subProject = session.find(SubProjectMstr.class, subProjectLabour.getSubProjectMstr().getId());
					subProjectLabour.setSubProjectMstr(subProject);
					subProjectLabour.setLabourMstr(labourMstrToPersist);
					subProjectLabour.setCreationDate(labourMstrToPersist.getCreationDate());
					session.save(subProjectLabour);
				}			
			}
		}
		
		if(origLabourMstr.getBiometricInfo() != null && origLabourMstr.getBiometricInfo().getProfileName() != null){
			BiometricInfo biometricInfo = origLabourMstr.getBiometricInfo();
			if(biometricInfo.getId() == 0) {
				biometricInfo.setLabourMstr(labourMstrToPersist);
				session.save(biometricInfo);
			}
			else {
				BiometricInfo origBiometricInfo = session.find(BiometricInfo.class, biometricInfo.getId());
				origBiometricInfo.setLabourMstr(labourMstrToPersist);
				origBiometricInfo.setProfileImage(biometricInfo.getProfileImage());
				origBiometricInfo.setProfileName(biometricInfo.getProfileName());
				session.merge(origBiometricInfo);
			}
		}
	}
	
	private void deleteCommonLabourDetails(int labourMstrId) {
		Session session = this.sessionFactory.getCurrentSession();		
		session.createNamedQuery("LabourSkill.DeleteByLabour").setParameter("labourMstrId", labourMstrId).executeUpdate();
		session.createNamedQuery("SubProjectLabour.DeleteByLabour").setParameter("labourMstrId", labourMstrId).executeUpdate();
		session.createNamedQuery("LabourSubContractor.DeleteByLabour").setParameter("labourMstrId", labourMstrId).executeUpdate();
	}
	
	private Communication saveCommunication(LabourMstr labourMstr) {
		Session session = this.sessionFactory.getCurrentSession();
		Communication communication = labourMstr.getCommunication();	
		Communication origCommunication = null;
		if(labourMstr.getCommunication().getId() != 0) {
			origCommunication = session.find(Communication.class, labourMstr.getCommunication().getId());
			
			origCommunication.setAadharNo(communication.getAadharNo());
			origCommunication.setBloodGroup(communication.getBloodGroup());
			origCommunication.setDateOfBirth(communication.getDateOfBirth());
			origCommunication.setDrivingLicenseNumber(communication.getDrivingLicenseNumber());
			origCommunication.setEmergencyContactName(communication.getEmergencyContactName());
			origCommunication.setEmergencyContactNumber(communication.getEmergencyContactNumber());
			origCommunication.setEsiNumber(communication.getEsiNumber());
			origCommunication.setIscurrentSame(communication.getIscurrentSame());
			origCommunication.setIsEmergencySame(communication.getIsEmergencySame());
			origCommunication.setKnownHealthIssue(communication.getKnownHealthIssue());
			//origCommunication.setMobileNumber(communication.getMobileNumber());
			origCommunication.setPanNumber(communication.getPanNumber());
			origCommunication.setPfNumber(communication.getPfNumber());
			origCommunication.setVoterId(communication.getVoterId());
			origCommunication.setModifiedDate(new Date());
		}
		else {
			origCommunication = labourMstr.getCommunication();
			origCommunication.setCreationDate(new Date());
		}
		
		Address permanentAddress = saveAddress(communication.getAddress1());
		origCommunication.setAddress1(permanentAddress);
			
		if(labourMstr.getCommunication().getIscurrentSame() == 0) {
			Address currentAddress = saveAddress(communication.getAddress2());
			origCommunication.setAddress2(currentAddress);
		}
		else {
			origCommunication.setAddress2(permanentAddress);
		}
		
		if(labourMstr.getCommunication().getIsEmergencySame() == 0) {
			Address emergencyAddress = saveAddress(communication.getAddress3());
			origCommunication.setAddress3(emergencyAddress);
		}
		
		if(communication.getBank() != null && communication.getBank().getBankName() != null) {
			Bank bank = saveBank(communication.getBank());		
			origCommunication.setBank(bank);
		}
		else {
			origCommunication.setBank(null);
		}
		
		if(origCommunication.getId() != 0) {
			origCommunication = (Communication) session.merge(origCommunication);
		}
		else {
			session.save(origCommunication);
		}		
		return origCommunication;		
	}	
	
	private Bank saveBank(Bank newBank) {
		Session session = this.sessionFactory.getCurrentSession();	
		Bank bank = null;
		if(newBank.getId() != 0) {
			bank = session.find(Bank.class, newBank.getId());
			bank.setAccNumber(newBank.getAccNumber());
			bank.setBankName(newBank.getBankName());
			bank.setBankBranch(newBank.getBankBranch());
			bank.setModifiedDate(new Date());
		}
		else {
			bank = newBank;
			bank.setCreationDate(new Date());
		}
		
		City city = (City) session.find(City.class, newBank.getCity().getId());
		bank.setCity(city);
		
		State state =  (State)session.find(State.class, newBank.getState().getId());
		bank.setState(state);	
		
		if(bank.getId() != 0) {
			bank = (Bank) session.merge(bank);
		}
		else {
			session.save(bank);
		}
		return bank;
	}
	
	private Address saveAddress(Address newAddress) {
		Session session = this.sessionFactory.getCurrentSession();		
		Address address = null;
		if(newAddress != null && newAddress.getPostalCode() != null) {
			if(newAddress.getId() != 0) {
				address = session.find(Address.class, newAddress.getId());
				address.setAddress1(newAddress.getAddress1());
				address.setAddress2(newAddress.getAddress2());
				address.setPostalCode(newAddress.getPostalCode());
				address.setModifiedDate(new Date());
			}
			else {
				address = newAddress;
				address.setCreationDate(new Date());				
			}
			City city = (City) session.find(City.class, address.getCity().getId());
			address.setCity(city);
			
			State state =  (State)session.find(State.class, address.getState().getId());
			address.setState(state);		
			if(address.getId() != 0) {
				address = (Address)session.merge(address);
			}
			else {
				session.save(address);
			}			
			return address;
		}
		else {
			return null;
		}		
	}
	//TODO: Not Used Anywhere
	public BiometricInfo saveLabourBiometricInfo(MultipartFile file,int labourId) {
		Session session = this.sessionFactory.getCurrentSession();		
		LabourMstr labourMstr = null;
		BiometricInfo biometricInfo = null;
		
		try{
			biometricInfo = (BiometricInfo) session.createNamedQuery("BiometricInfo.findByLabourId").
					setParameter("labourId", labourId).getSingleResult();
		}
		catch(NoResultException e) {
		
		}
		
		if(biometricInfo != null ){
			try {	
				biometricInfo.setProfileImage(file.getBytes());
				biometricInfo.setProfileName(file.getOriginalFilename());
				biometricInfo = (BiometricInfo) session.merge(biometricInfo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				labourMstr = session.find(LabourMstr.class, labourId);	
			}
			catch(NoResultException e) {
				//no labour
			}
			
			if(labourMstr != null) {
				try {	
					biometricInfo = new BiometricInfo();
					biometricInfo.setLabourMstr(labourMstr);
					biometricInfo.setProfileImage(file.getBytes());
					biometricInfo.setProfileName(file.getOriginalFilename());
					session.save(biometricInfo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		return biometricInfo;
	}

}
