package com.trivecta.attendencetracker.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class UserLogin {

	private String userName;

	private String psswd;
	
	@ManagedProperty("#{menuRender}")
	private MenuRender menuRender;
	
	@ManagedProperty("#{labourCreate}")
	private LabourCreate labourCreate;
	
	
	public String login() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean loggedIn = false;
		
		if (userName != null && userName.equals("admin") && psswd != null && psswd.equals("admin")) {
			loggedIn = true;
			menuRender.initAdminUser();
			labourCreate.init();
			return "/labour/create";
			//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", userName);
		} 
		else if (userName != null && userName.equals("test") && psswd != null && psswd.equals("test")) {
			loggedIn = true;
			menuRender.initEmployee();
			labourCreate.init();
			return "/attendence/create";
			//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", userName);
		} 
		else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}		
		//context.addCallbackParam("loggedIn", loggedIn);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPsswd() {
		return psswd;
	}

	public void setPsswd(String psswd) {
		this.psswd = psswd;
	}

	public LabourCreate getLabourCreate() {
		return labourCreate;
	}

	public void setLabourCreate(LabourCreate labourCreate) {
		this.labourCreate = labourCreate;
	}

	public MenuRender getMenuRender() {
		return menuRender;
	}

	public void setMenuRender(MenuRender menuRender) {
		this.menuRender = menuRender;
	}
}
