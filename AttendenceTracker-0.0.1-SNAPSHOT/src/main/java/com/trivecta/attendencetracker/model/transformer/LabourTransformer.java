package com.trivecta.attendencetracker.model.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.trivecta.attendencetracker.model.AddressVO;
import com.trivecta.attendencetracker.model.AttendanceTrackerVO;
import com.trivecta.attendencetracker.model.DropDownVO;
import com.trivecta.attendencetracker.model.LabourSearchVO;
import com.trivecta.attendencetracker.model.LabourVO;
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
import com.trivecta.attendencetracker.model.service.LabourService;

@Component
public class LabourTransformer {

	@Autowired
	LabourService labourService;
		
	public List<DropDownVO> getAllLabourNames() {
		List<DropDownVO> nameVOList = new ArrayList<DropDownVO>();
		List<LabourMstr> labourList = labourService.getAllLabourMstr();
		if(labourList != null && labourList.size() > 0) {
			for(LabourMstr labourMstr : labourList) {
				DropDownVO nameVo =
						new DropDownVO(labourMstr.getId(),labourMstr.getFirstName()+" "+labourMstr.getLastName());
				nameVOList.add(nameVo);				
			}
		}
		return nameVOList;		
	}

	public List<LabourSearchVO> getLabourDetails(LabourSearchVO labour){
		List<LabourSearchVO> labourSearchList = new ArrayList<LabourSearchVO>();
		
		LabourMstr labourMstr = new LabourMstr();
		labourMstr.setNickName(labour.getNickName());
		if(labour.getMobNo() != null || (labour.getBloodGroup()!= null && labour.getBloodGroup().trim().length() >0 ) || labour.getCityId() != 0) {
			Communication communication = new Communication();
			communication.setMobileNumber(labour.getMobNo());
			if(!"".equalsIgnoreCase(labour.getBloodGroup())){
				communication.setBloodGroup(labour.getBloodGroup());
			}
			if(labour.getCityId() != 0) {
				Address address = new Address();
				City city = new City();
				city.setId(labour.getCityId());
				address.setCity(city);
				communication.setAddress1(address);
			}
			labourMstr.setCommunication(communication);
		}	
		
		List<LabourMstr> labourMstrList = labourService.getLabourDetails(labourMstr);
		
		if(labourMstrList != null && labourMstrList.size() >0) {		
			for(LabourMstr labourMstrDetail :labourMstrList){
				LabourSearchVO labourSearch = new LabourSearchVO();
				labourSearch.setId(labourMstrDetail.getId());
				labourSearch.setName(labourMstrDetail.getFirstName()+ " "+labourMstrDetail.getLastName());
				labourSearch.setNickName(labourMstrDetail.getNickName());
				if(labourMstrDetail.getCommunication() != null) {
					labourSearch.setMobNo(labourMstrDetail.getCommunication().getMobileNumber());
					String currentAddress = null;
					if(labourMstrDetail.getCommunication().getIscurrentSame() == 0) {
						currentAddress = concatAddress(labourMstrDetail.getCommunication().getAddress2());		
					}
					else {
						currentAddress = concatAddress(labourMstrDetail.getCommunication().getAddress1());						
					}
					labourSearch.setAddress(currentAddress);
					labourSearch.setBloodGroup(labourMstrDetail.getCommunication().getBloodGroup());
					labourSearch.setEmergencyContact(labourMstrDetail.getCommunication().getEmergencyContactName());
					labourSearch.setEmergencyContactNo(labourMstrDetail.getCommunication().getEmergencyContactNumber());
					labourSearchList.add(labourSearch);
				}				
			}
		}		
		return labourSearchList;
	}
	
	private String concatAddress(Address address ) {
		StringBuffer addressStr = new StringBuffer();
		if(address != null && address.getId()!= 0){
			addressStr.append(address.getAddress1()).append("\n").
			append(address.getAddress2()).append("\n").
			append(address.getCity().getName()).append("\n").
			append(address.getState().getName()).append(" ").append(address.getPostalCode());
			return addressStr.toString();
		}
		return null;
		
	}
	
	public LabourVO getLabour(int labourId){
		LabourMstr labourMstr =labourService.getLabourById(labourId);
		LabourVO labour = setLabourFromMstr(labourMstr);
		return labour;
	}	
	
	public int saveLabourMstr(LabourVO labourVo) throws AttendenceTrackerException {
		LabourMstr labourMstr = setLabourMstrFromVO(labourVo);
		labourMstr = labourService.saveLabour(labourMstr);
		return labourMstr.getId();
	}	
	
	private LabourMstr setLabourMstrFromVO(LabourVO labourVO){
		LabourMstr labour = new LabourMstr();
		
		labour.setId(labourVO.getId());
		labour.setFirstName(labourVO.getFirstName());
		labour.setLastName(labourVO.getLastName());
		labour.setNickName(labourVO.getNickName());
		
		Communication communication = new Communication();
		communication.setId(labourVO.getCommunicationId());
		communication.setDateOfBirth(labourVO.getDob());
		communication.setMobileNumber(labourVO.getMobNo());
		communication.setBloodGroup(labourVO.getBloodGroup());
		communication.setPfNumber(labourVO.getPfNo());
		communication.setEsiNumber(labourVO.getEsiNo());
		communication.setKnownHealthIssue(labourVO.getKnownHealthIssues());
		
		communication.setAddress1(getAddressFromVO(labourVO.getPermanentAddress()));
	
		if(labourVO.isCurrentSame()) {
			communication.setIscurrentSame(1);
		}
		else {
			communication.setIscurrentSame(0);
			Address currentAddress = getAddressFromVO(labourVO.getCurrentAddress());
			communication.setAddress2(currentAddress);
		}
		communication.setAadharNo(labourVO.getAadharNo());
		communication.setVoterId(labourVO.getVoterId());
		communication.setDrivingLicenseNumber(labourVO.getDrivingLicencseNo());
		communication.setPanNumber(labourVO.getPanNo());
		
		Bank bank = null;
		if(labourVO.getBankAccNo() != null) {
			bank = new Bank();
			bank.setId(labourVO.getBankId());
			//Accno
			bank.setBankName(labourVO.getBankName());
			bank.setBankBranch(labourVO.getBranch());
			bank.setIfscCode(labourVO.getIfscCode());
			if(labourVO.getBankCityId() != 0){
				City city = new City();
				city.setId(labourVO.getBankCityId());
				//city
			}
			if(labourVO.getBankStateId() !=0){
				State state = new State();
				state.setId(labourVO.getBankStateId());
				//state
			}		
		}
		
		communication.setEmergencyContactName(labourVO.getEmergencyContactName());
		communication.setEmergencyContactNumber(labourVO.getEmergencyContactNo());
		
		if(labourVO.isEmergencySame()){
			communication.setIsEmergencySame(1);
		}
		else {
			communication.setIsEmergencySame(0);
			Address emergencyAddress = getAddressFromVO(labourVO.getEmergencyContactAddress());
			communication.setAddress3(emergencyAddress);
		}
		labour.setCommunication(communication);
		
		
		if(labourVO.getProfileName() != null ) {
			BiometricInfo biometricInfo = new BiometricInfo();
			biometricInfo.setId(labourVO.getBiometricInfoId());
			biometricInfo.setProfileImage(labourVO.getProfileImage());
			biometricInfo.setProfileName(labourVO.getProfileName());
			labour.setBiometricInfo(biometricInfo);
		}	
		else {
			labour.setBiometricInfo(null);
		}
		
		if(labourVO.getSkills() != null && labourVO.getSkills().length >0) {
			List<LabourSkill> labourSkills = new ArrayList<LabourSkill>();
			for(int skillId : labourVO.getSkills()){
				LabourSkill labourSkill = new LabourSkill();
				Skill skill = new Skill();
				skill.setId(skillId);
				labourSkill.setSkill(skill);
				labourSkills.add(labourSkill);
			}	
			labour.setLabourSkills(labourSkills);
		}
		else {
			labour.setLabourSkills(null);
		}
		
		if(labourVO.getSubContractId() != 0) {
			LabourSubContractor labourSubContractor = new LabourSubContractor();
			SubContractor subContractor  = new SubContractor();
			subContractor.setId(labourVO.getSubContractId());
			labourSubContractor.setSubContractor(subContractor);
			
			List<LabourSubContractor> subContractors = new ArrayList<LabourSubContractor>();			
			subContractors.add(labourSubContractor);
			
			labour.setLabourSubContractors(subContractors);
		}
		else {
			labour.setLabourSubContractors(null);
		}
		
		if(labourVO.getSubProjectId() != 0){
			SubProjectMstr subProject = new SubProjectMstr();
			subProject.setId(labourVO.getSubProjectId());
			
			SubProjectLabour subProjectLabour = new SubProjectLabour();
			subProjectLabour.setSubProjectMstr(subProject);
			List<SubProjectLabour> subProjectLabours = new ArrayList<SubProjectLabour>();
			subProjectLabours.add(subProjectLabour);
			labour.setSubProjectLabours(subProjectLabours);
		}
		else {
			labour.setSubProjectLabours(null);
		}
		return labour;
	}
	
	
	private Address getAddressFromVO(AddressVO addressVO) {
		Address permanentAddress = null;
		if(addressVO != null && addressVO.getPostalCode() != null) {
			permanentAddress = new Address();
			permanentAddress.setId(addressVO.getAddressId());
			permanentAddress.setAddress1(addressVO.getAddressLine1());
			permanentAddress.setAddress2(addressVO.getAddressLine2());
			City city = new City();
			city.setId(addressVO.getCityId());
			permanentAddress.setCity(city);
			State state = new State();
			state.setId(addressVO.getStateId());
			permanentAddress.setState(state);
			permanentAddress.setPostalCode(addressVO.getPostalCode());
		}
		
		return permanentAddress;
	}
	
	private LabourVO setLabourFromMstr(LabourMstr labourMstr) {
		LabourVO labour = new LabourVO();
		labour.setId(labourMstr.getId());
		labour.setFirstName(labourMstr.getFirstName());
		labour.setLastName(labourMstr.getLastName());
		labour.setNickName(labourMstr.getNickName());
		
		if(labourMstr.getCommunication()!=null) {
			labour.setCommunicationId(labourMstr.getCommunication().getId());
			labour.setDob(labourMstr.getCommunication().getDateOfBirth());
			labour.setMobNo(labourMstr.getCommunication().getMobileNumber());
			labour.setBloodGroup(labourMstr.getCommunication().getBloodGroup());
			labour.setPfNo(labourMstr.getCommunication().getPfNumber());
			labour.setEsiNo(labourMstr.getCommunication().getEsiNumber());
			labour.setKnownHealthIssues(labourMstr.getCommunication().getKnownHealthIssue());
			
			AddressVO permanentAddress = getAddress(labourMstr.getCommunication().getAddress1());
			labour.setPermanentAddress(permanentAddress);
			
			AddressVO currentAddress = new AddressVO();
			if(labourMstr.getCommunication().getIscurrentSame() ==1){
				labour.setCurrentSame(true);
				currentAddress = getAddress(labourMstr.getCommunication().getAddress1());
				
			}
			else
			{
				labour.setCurrentSame(false);
				currentAddress = getAddress(labourMstr.getCommunication().getAddress2());
			}				
			labour.setCurrentAddress(currentAddress);
							
			labour.setCurrentAddress(currentAddress);
			
			labour.setAadharNo(labourMstr.getCommunication().getAadharNo());
			labour.setVoterId(labourMstr.getCommunication().getVoterId());
			labour.setDrivingLicencseNo(labourMstr.getCommunication().getDrivingLicenseNumber());
			labour.setPanNo(labourMstr.getCommunication().getPanNumber());
			
			if(labourMstr.getCommunication().getBank() != null) {
				Bank bank = labourMstr.getCommunication().getBank();
				labour.setBankId(bank.getId());
				labour.setBankAccNo(bank.getAccNumber());
				labour.setIfscCode(bank.getIfscCode());
				labour.setBankName(bank.getBankName());
				labour.setBranch(bank.getBankBranch());
				if(bank.getCity() != null) {
					labour.setBankCityId(bank.getCity().getId());
				}
				else {
					labour.setBankCityId(0);
				}
				if(bank.getState() != null) {
					labour.setBankStateId(bank.getState().getId());
				}
				else {
					labour.setBankStateId(0);
				}								
			}
						
			labour.setEmergencyContactName(labourMstr.getCommunication().getEmergencyContactName());
			labour.setEmergencyContactNo(labourMstr.getCommunication().getEmergencyContactNumber());
			
			AddressVO emergencyAddress = new AddressVO();
			if(labourMstr.getCommunication().getIsEmergencySame() ==1){
				labour.setEmergencySame(true);
				emergencyAddress = getAddress(labourMstr.getCommunication().getAddress1());				
			}
			else {
				labour.setEmergencySame(false);
				emergencyAddress = getAddress(labourMstr.getCommunication().getAddress3());
			}				
			labour.setEmergencyContactAddress(emergencyAddress);
		}		
		
		if(labourMstr.getLabourSkills() != null && labourMstr.getLabourSkills().size() >0){
			int[] skillArray = new int[labourMstr.getLabourSkills().size()];
			int i = 0;
			for(LabourSkill labourSkill : labourMstr.getLabourSkills()) {
				skillArray[i++] = labourSkill.getSkill().getId();
			}
			labour.setSkills(skillArray);
		}
		
		if(labourMstr.getLabourSubContractors()!= null && labourMstr.getLabourSubContractors().size() > 0) {
			if(labourMstr.getLabourSubContractors().get(0).getSubContractor() != null) {
				int subContractId = labourMstr.getLabourSubContractors().get(0).getSubContractor().getId();
				labour.setSubContractId(subContractId);
			}
		}
		
		if(labourMstr.getBiometricInfo() != null) {
			labour.setBiometricInfoId(labourMstr.getBiometricInfo().getId());
			labour.setProfileImage(labourMstr.getBiometricInfo().getProfileImage());
			labour.setProfileName(labourMstr.getBiometricInfo().getProfileName());
		}
		
		if(labourMstr.getSubProjectLabours() != null && labourMstr.getSubProjectLabours().size() > 0){
			if(labourMstr.getSubProjectLabours().get(0).getSubProjectMstr() != null) {
				labour.setSubProjectId(labourMstr.getSubProjectLabours().get(0).getSubProjectMstr().getId());
				labour.setProjectId(labourMstr.getSubProjectLabours().get(0).getSubProjectMstr().getProject().getId());
			}
		}
		return labour;
	}
	
	private AddressVO getAddress(Address address) {
		AddressVO permanentAddress = new AddressVO();
		if(address != null) {
			permanentAddress.setAddressId(address.getId());
			permanentAddress.setAddressLine1(address.getAddress1());
			permanentAddress.setAddressLine2(address.getAddress2());
			if(address.getCity() != null)
				permanentAddress.setCityId(address.getCity().getId());
			if(address.getState() != null)
				permanentAddress.setStateId(address.getState().getId());
			permanentAddress.setPostalCode(address.getPostalCode());
		}		
		return permanentAddress;
	}	
}
