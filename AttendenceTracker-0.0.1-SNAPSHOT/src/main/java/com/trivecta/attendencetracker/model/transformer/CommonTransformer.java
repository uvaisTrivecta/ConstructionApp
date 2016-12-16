package com.trivecta.attendencetracker.model.transformer;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trivecta.attendencetracker.model.entity.City;
import com.trivecta.attendencetracker.model.entity.Project;
import com.trivecta.attendencetracker.model.entity.Skill;
import com.trivecta.attendencetracker.model.entity.State;
import com.trivecta.attendencetracker.model.entity.SubContractor;
import com.trivecta.attendencetracker.model.entity.SubProjectMstr;
import com.trivecta.attendencetracker.model.service.CommonService;

@Component
public class CommonTransformer {

	@Autowired
	CommonService commonService;
	
	public List<SelectItem> getAllSubContractor(){
		List<SelectItem> subContractorVOList = new ArrayList<SelectItem>();
		
		List<SubContractor> subContractorList = commonService.getAllSubContractor();	
		
		subContractorVOList.add(new SelectItem(0, "Select Subcontractor"));
		
		if(subContractorList != null && subContractorList.size() > 0) {
			for(SubContractor subContract : subContractorList) {
				SelectItem subContractorVO =
						new SelectItem(subContract.getId(),subContract.getName());
				subContractorVOList.add(subContractorVO);				
			}
		}
		return subContractorVOList;
	}
	
	public List<SelectItem> getAllSkills() {
		List<SelectItem> skillVOList = new ArrayList<SelectItem>();
		
		List<Skill> skillList = commonService.getAllSkills();
		
		if(skillList != null && skillList.size() > 0) {
			for(Skill skill :skillList){
				SelectItem skillVO =
						new SelectItem(skill.getId(),skill.getSkill());
				skillVOList.add(skillVO);
			}
		}
		return skillVOList;
	}
	
	public List<SelectItem> getAllCities(){
		List<SelectItem> citiVOList = new ArrayList<SelectItem>();
		
		List<City> cityList = commonService.getAllCities();
		
		citiVOList.add(new SelectItem(0, "Select City"));
		
		if(cityList != null && cityList.size() > 0) {
			for(City city : cityList) {
				SelectItem cityVO =
						new SelectItem(city.getId(),city.getName());
				citiVOList.add(cityVO);				
			}
		}
		return citiVOList;
	}
	
	public List<SelectItem> getAllStates(){
		List<SelectItem> stateVOList = new ArrayList<SelectItem>();
		
		List<State> stateList = commonService.getAllStates();
		
		stateVOList.add(new SelectItem(0, "Select State"));
		
		if(stateList != null && stateList.size() > 0) {
			for(State state : stateList) {
				SelectItem stateVO =
						new SelectItem(state.getId(),state.getName());
				stateVOList.add(stateVO);				
			}
		}
		return stateVOList;
	}	
	
	public List<SelectItem> getAllProjects(){
		List<SelectItem> projectVOList = new ArrayList<SelectItem>();
		
		List<Project> projectList = getAllProjectsInList();
		
		projectVOList.add(new SelectItem(0, "Select Project"));
		
		if(projectList != null && projectList.size() > 0) {
			for(Project project : projectList) {
				SelectItem stateVO =
						new SelectItem(project.getId(),project.getName());
				projectVOList.add(stateVO);				
			}
		}
		return projectVOList;
	}	
	
	public List<SelectItem> getAllSubProjectsByProject(int projectId){
		List<SelectItem> subProjectVOList = new ArrayList<SelectItem>();
		subProjectVOList.add(new SelectItem(0, "Select Sub Project"));
		
		if(projectId != 0) {
			List<SubProjectMstr> subProjList = getAllSubProjectsInList(projectId);	
			
			if(subProjList != null && subProjList.size() > 0) {
				for(SubProjectMstr subProj : subProjList) {
					SelectItem stateVO =
							new SelectItem(subProj.getId(),subProj.getName());
					subProjectVOList.add(stateVO);				
				}
			}
		}		
		return subProjectVOList;
	}	
	
	public List<Project> getAllProjectsInList() {
		List<Project> projectList = commonService.getAllProjects();
		return projectList;
	}
	
	public List<SubProjectMstr> getAllSubProjectsInList(int projectId) {
		List<SubProjectMstr> subProjList = commonService.getSubProjectByProjectId(projectId);	
		return subProjList;
	}
}
