package com.trivecta.attendencetracker.prime.faces.beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.trivecta.attendencetracker.model.LabourVO;
import com.trivecta.attendencetracker.model.exception.AttendenceTrackerException;
import com.trivecta.attendencetracker.model.transformer.CommonTransformer;
import com.trivecta.attendencetracker.model.transformer.LabourTransformer;

@ManagedBean
@SessionScoped
public class LabourViewEdit {

	@ManagedProperty("#{labourTransformer}")
	private LabourTransformer labourTransformer;
	
	@ManagedProperty("#{commonTransformer}")
	private CommonTransformer commonTransformer;
	
	private LabourVO labourVO;

	private List<SelectItem> skillVOs;
	
	private List<SelectItem> subContractVOs;
		
	private List<SelectItem> cityVOs;
	
	private List<SelectItem> stateVOs;
	
	UploadedFile file;
	
	private boolean currentAddressDisable;
	
	private boolean emergencyAddressDisable;
	
	private List<SelectItem> projectVOs;
	
	private List<SelectItem> subProjectVOs;
	
	private StreamedContent profileImage;
	
	public void initViewEdit(int labourId) {
		skillVOs = commonTransformer.getAllSkills();
		subContractVOs = commonTransformer.getAllSubContractor();
		cityVOs = commonTransformer.getAllCities();
		stateVOs = commonTransformer.getAllStates();
		projectVOs = commonTransformer.getAllProjects();
		subProjectVOs = commonTransformer.getAllSubProjectsByProject(0);
		
		this.labourVO =labourTransformer.getLabour(labourId);
		profileImage = getImageFromDB();
		getSubProjectByProjectId();
		updateCurrentAddress();
		updateEmergency();
	}
	
	private DefaultStreamedContent getImageFromDB() {
		System.out.println(" get Image gets called");
		System.out.println(" image name :: "+labourVO.getProfileName());
		FacesContext context = FacesContext.getCurrentInstance();

	   if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	    }
	   else {
		   if(labourVO.getProfileImage() != null){
				return new DefaultStreamedContent(new ByteArrayInputStream(labourVO.getProfileImage()),"image/png");
		   }
		   else {
			   return new DefaultStreamedContent();
		   }
				
	   }
	}

	public String editLabour() {
		initViewEdit(labourVO.getId());
		return "/labour/edit";
	}
	
	public  String save() {
		/*byte[] contents;
		try {
			contents = IOUtils.toByteArray(file.getInputstream());			
			labourVO.setProfileImage(contents);
			labourVO.setProfileName(file.getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/	
		
		try {
			labourTransformer.saveLabourMstr(labourVO);
		} catch (AttendenceTrackerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initViewEdit(labourVO.getId());
		return "/labour/view";
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

	public StreamedContent getProfileImage() {
		profileImage = getImageFromDB();
		return profileImage;
	}

	public void setProfileImage(StreamedContent profileImage) {
		this.profileImage = profileImage;
	}
}
