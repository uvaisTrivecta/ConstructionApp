package com.trivecta.attendencetracker.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trivecta.attendencetracker.model.entity.City;
import com.trivecta.attendencetracker.model.entity.Project;
import com.trivecta.attendencetracker.model.entity.Skill;
import com.trivecta.attendencetracker.model.entity.State;
import com.trivecta.attendencetracker.model.entity.SubContractor;
import com.trivecta.attendencetracker.model.entity.SubProjectMstr;

@Repository
public class CommonDAOImpl implements CommonDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<SubContractor> getAllSubContractor() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("SubContractor.findAll");
		List<SubContractor> subContractorList = query.getResultList();
		return subContractorList;
	}
	
	public List<Skill> getAllSkills() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("Skill.findAll");
		List<Skill> skillList = query.getResultList();
		return skillList;
	}
	
	public List<City> getAllCities() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("City.findAll");
		List<City> cities = query.getResultList();
		return cities;
	}
	
	public List<State> getAllStates() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("State.findAll");
		List<State> states = query.getResultList();
		return states;
	}
	
	public List<Project> getAllProjects() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("Project.findAll");
		List<Project> projects = query.getResultList();
		return projects;
	}
	
	public List<SubProjectMstr> getSubProjectByProjectId(int projectId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("SubProjectMstr.findByProject").setParameter("projectId", projectId);
		List<SubProjectMstr> subProjects = query.getResultList();
		return subProjects;
	}
}
