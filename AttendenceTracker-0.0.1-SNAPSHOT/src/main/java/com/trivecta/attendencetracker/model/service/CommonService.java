package com.trivecta.attendencetracker.model.service;

import java.util.List;

import com.trivecta.attendencetracker.model.entity.City;
import com.trivecta.attendencetracker.model.entity.Project;
import com.trivecta.attendencetracker.model.entity.Skill;
import com.trivecta.attendencetracker.model.entity.State;
import com.trivecta.attendencetracker.model.entity.SubContractor;
import com.trivecta.attendencetracker.model.entity.SubProjectMstr;

public interface CommonService {
	public List<SubContractor> getAllSubContractor();
	public List<Skill> getAllSkills();
	public List<City> getAllCities();
	public List<State> getAllStates();
	public List<Project> getAllProjects();
	public List<SubProjectMstr> getSubProjectByProjectId(int projectId);
}
