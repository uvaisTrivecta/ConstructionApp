package com.trivecta.attendencetracker.prime.faces.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.trivecta.attendencetracker.model.entity.Project;
import com.trivecta.attendencetracker.model.entity.SubProjectMstr;
import com.trivecta.attendencetracker.model.transformer.AttendenceTransformer;
import com.trivecta.attendencetracker.model.transformer.CommonTransformer;

@ManagedBean
@SessionScoped
public class Footer {

	private String projectName;
	
	private int totalCount;
	
	private int inCount;
	
	private int outCount;

	@ManagedProperty("#{attendenceTransformer}")
	private AttendenceTransformer attendenceTransformer;
	
	@ManagedProperty("#{commonTransformer}")
	private CommonTransformer commonTransformer;
	
	public void init() {	
		totalCount = 0;
		inCount = 0;
		outCount = 0;
		projectName = "";
		List<Project> projects = commonTransformer.getAllProjectsInList();
		if(projects != null && projects.size() > 0) {
			int projectId = projects.get(0).getId();
			projectName = projects.get(0).getName();
			List<SubProjectMstr> subProjList = commonTransformer.getAllSubProjectsInList(projectId);
			if(subProjList != null && subProjList.size() > 0) {
				int subProjectId = subProjList.get(0).getId();
				projectName = projectName+" "+subProjList.get(0).getName();
				int[] count = attendenceTransformer.getAttendenceCountBySubProjectId(subProjectId);
				totalCount = count[0];
				inCount = count[1];
				outCount = count[2];			
			}			
		}		
	}
		
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getInCount() {
		return inCount;
	}

	public void setInCount(int inCount) {
		this.inCount = inCount;
	}

	public int getOutCount() {
		return outCount;
	}

	public void setOutCount(int outCount) {
		this.outCount = outCount;
	}

	public AttendenceTransformer getAttendenceTransformer() {
		return attendenceTransformer;
	}

	public void setAttendenceTransformer(AttendenceTransformer attendenceTransformer) {
		this.attendenceTransformer = attendenceTransformer;
	}

	public CommonTransformer getCommonTransformer() {
		return commonTransformer;
	}

	public void setCommonTransformer(CommonTransformer commonTransformer) {
		this.commonTransformer = commonTransformer;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
