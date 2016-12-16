package com.trivecta.attendencetracker.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivecta.attendencetracker.model.dao.CommonDAO;
import com.trivecta.attendencetracker.model.entity.City;
import com.trivecta.attendencetracker.model.entity.Project;
import com.trivecta.attendencetracker.model.entity.Skill;
import com.trivecta.attendencetracker.model.entity.State;
import com.trivecta.attendencetracker.model.entity.SubContractor;
import com.trivecta.attendencetracker.model.entity.SubProjectMstr;

@Service("commonService")
public class CommonServiceImpl implements CommonService{

	@Autowired
	CommonDAO commonDAO;
	
	@Transactional
	public List<SubContractor> getAllSubContractor(){
		return commonDAO.getAllSubContractor();
	}
	
	@Transactional
	public List<Skill> getAllSkills() {
		return commonDAO.getAllSkills();
	}
	
	@Transactional
	public List<City> getAllCities() {
		return commonDAO.getAllCities();
	}
	
	@Transactional
	public List<State> getAllStates() {
		return commonDAO.getAllStates();
	}
	
	@Transactional
	public List<Project> getAllProjects() {
		return commonDAO.getAllProjects();
	}
	
	@Transactional
	public List<SubProjectMstr> getSubProjectByProjectId(int projectId){
		return commonDAO.getSubProjectByProjectId(projectId);
	}
	
	
}
