package com.trivecta.attendencetracker.prime.faces.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.trivecta.attendencetracker.model.AddressVO;
import com.trivecta.attendencetracker.model.LabourVO;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;
import com.trivecta.attendencetracker.model.transformer.CommonTransformer;
import com.trivecta.attendencetracker.model.transformer.LabourTransformer;

@ManagedBean
@SessionScoped
public class LabourCreate {

	@ManagedProperty("#{labourTransformer}")
	private LabourTransformer labourTransformer;
	
	@ManagedProperty("#{commonTransformer}")
	private CommonTransformer commonTransformer;
	
	@ManagedProperty("#{labourViewEdit}")
	private LabourViewEdit labourViewEdit;
	
	private LabourVO labourVO;

	private List<SelectItem> skillVOs;
	
	private List<SelectItem> subContractVOs;
		
	private List<SelectItem> cityVOs;
	
	private List<SelectItem> stateVOs;
	
	private List<SelectItem> projectVOs;
	
	private List<SelectItem> subProjectVOs;
	
	UploadedFile file;
	
	private boolean currentAddressDisable;
	
	private boolean emergencyAddressDisable;
	
	public void init() {
		labourVO = new LabourVO();
		
		AddressVO addressVO = new AddressVO();
		labourVO.setPermanentAddress(addressVO);
		AddressVO currentAddressVO = new AddressVO();
		labourVO.setCurrentAddress(currentAddressVO);
		AddressVO emergencyAddressVO = new AddressVO();
		labourVO.setEmergencyContactAddress(emergencyAddressVO);
				
		skillVOs = commonTransformer.getAllSkills();
		subContractVOs = commonTransformer.getAllSubContractor();
		cityVOs = commonTransformer.getAllCities();
		stateVOs = commonTransformer.getAllStates();
		projectVOs = commonTransformer.getAllProjects();
		subProjectVOs = commonTransformer.getAllSubProjectsByProject(0);
		
		currentAddressDisable = false;
		emergencyAddressDisable = false;
	}
	
	public String save() {
		byte[] contents;
		try {
			contents = IOUtils.toByteArray(file.getInputstream());			
			labourVO.setProfileImage(contents);
			labourVO.setProfileName(file.getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		    		
		try {
			
			System.out.println(" create : labourVO.getSubProjectId :"+labourVO.getSubProjectId());
			int labourMstrId = labourTransformer.saveLabourMstr(labourVO);
			labourViewEdit.initViewEdit(labourMstrId);
			return "/labour/view";
		} catch (AttendenceTrackerException e) {
			 FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary",e.getMessage());
		     FacesContext.getCurrentInstance().addMessage("msgs", facesMsg);
		}
		return null;		
	}
	
	public void getSubProjectByProjectId() {
		subProjectVOs = commonTransformer.getAllSubProjectsByProject(labourVO.getProjectId());
	}
	
	public void updateCurrentAddress() {
		if(labourVO.isCurrentSame() == true) {
			currentAddressDisable = true;
		}
		else {
			currentAddressDisable = false;
		}
	}
	
	public void updateEmergency() {
		if(labourVO.isEmergencySame() == true) {
			emergencyAddressDisable = true;
		}
		else {
			emergencyAddressDisable = false;
		}
	}
	
	public LabourVO getLabourVO() {
		return labourVO;
	}

	public void setLabourVO(LabourVO labourVO) {
		this.labourVO = labourVO;
	}

	public List<SelectItem> getSkillVOs() {
		return skillVOs;
	}

	public void setSkillVOs(List<SelectItem> skillVOs) {
		this.skillVOs = skillVOs;
	}

	public List<SelectItem> getSubContractVOs() {
		return subContractVOs;
	}

	public void setSubContractVOs(List<SelectItem> subContractVOs) {
		this.subContractVOs = subContractVOs;
	}

	public List<SelectItem> getCityVOs() {
		return cityVOs;
	}

	public void setCityVOs(List<SelectItem> cityVOs) {
		this.cityVOs = cityVOs;
	}

	public List<SelectItem> getStateVOs() {
		return stateVOs;
	}

	public void setStateVOs(List<SelectItem> stateVOs) {
		this.stateVOs = stateVOs;
	}

	public LabourTransformer getLabourTransformer() {
		return labourTransformer;
	}

	public void setLabourTransformer(LabourTransformer labourTransformer) {
		this.labourTransformer = labourTransformer;
	}

	public CommonTransformer getCommonTransformer() {
		return commonTransformer;
	}

	public void setCommonTransformer(CommonTransformer commonTransformer) {
		this.commonTransformer = commonTransformer;
	}

	public LabourViewEdit getLabourViewEdit() {
		return labourViewEdit;
	}

	public void setLabourViewEdit(LabourViewEdit labourViewEdit) {
		this.labourViewEdit = labourViewEdit;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isCurrentAddressDisable() {
		return currentAddressDisable;
	}

	public void setCurrentAddressDisable(boolean currentAddressDisable) {
		this.currentAddressDisable = currentAddressDisable;
	}

	public boolean isEmergencyAddressDisable() {
		return emergencyAddressDisable;
	}

	public void setEmergencyAddressDisable(boolean emergencyAddressDisable) {
		this.emergencyAddressDisable = emergencyAddressDisable;
	}

	public List<SelectItem> getProjectVOs() {
		return projectVOs;
	}

	public void setProjectVOs(List<SelectItem> projectVOs) {
		this.projectVOs = projectVOs;
	}

	public List<SelectItem> getSubProjectVOs() {
		return subProjectVOs;
	}

	public void setSubProjectVOs(List<SelectItem> subProjectVOs) {
		this.subProjectVOs = subProjectVOs;
	}

	
}
