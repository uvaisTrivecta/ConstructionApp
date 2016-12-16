package com.trivecta.attendencetracker.prime.faces.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
public class Menu {
	
	
	
	@ManagedProperty("#{labourSearch}")
	private LabourSearch labourSearch;

	@ManagedProperty("#{labourCreate}")
	private LabourCreate labourCreate;
	
	@ManagedProperty("#{attendenceEntry}")
	private AttendenceEntry attendenceEntry;
	
	@ManagedProperty("#{attendenceSearch}")
	private AttendenceSearch attendenceSearch;
		
	public String createLabour() {
		labourCreate.init();
		return "/labour/create";
	}
	
	public String searchLabour() {
		labourSearch.init();
		return "/labour/search";
	}
	
	public String makeAttendence() {
		attendenceEntry.init();
		return "/attendence/create";
	}
	
	public String attendenceReport() {
		attendenceSearch.init();
		return "/attendence/search";		
	}

	public LabourSearch getLabourSearch() {
		return labourSearch;
	}

	public void setLabourSearch(LabourSearch labourSearch) {
		this.labourSearch = labourSearch;
	}

	public LabourCreate getLabourCreate() {
		return labourCreate;
	}

	public void setLabourCreate(LabourCreate labourCreate) {
		this.labourCreate = labourCreate;
	}

	public AttendenceEntry getAttendenceEntry() {
		return attendenceEntry;
	}

	public void setAttendenceEntry(AttendenceEntry attendenceEntry) {
		this.attendenceEntry = attendenceEntry;
	}

	public AttendenceSearch getAttendenceSearch() {
		return attendenceSearch;
	}

	public void setAttendenceSearch(AttendenceSearch attendenceSearch) {
		this.attendenceSearch = attendenceSearch;
	}

	
}
