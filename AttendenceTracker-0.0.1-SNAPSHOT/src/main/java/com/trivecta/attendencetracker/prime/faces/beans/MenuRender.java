package com.trivecta.attendencetracker.prime.faces.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MenuRender {

	private boolean resourceRendered ;
	
	private boolean attendanceRendered ;
	
	@ManagedProperty("#{footer}")
	private Footer footer;
	
	public void init() {
		resourceRendered =  true;
		attendanceRendered = true;
		footer.init();
	}
	
	public void initAdminUser() {
		init();
	}
	
	public void initEmployee() {
		resourceRendered = false;
		attendanceRendered = true;
		footer.init();
	}

	public boolean isResourceRendered() {
		return resourceRendered;
	}

	public void setResourceRendered(boolean resourceRendered) {
		this.resourceRendered = resourceRendered;
	}

	public boolean isAttendanceRendered() {
		return attendanceRendered;
	}

	public void setAttendanceRendered(boolean attendanceRendered) {
		this.attendanceRendered = attendanceRendered;
	}

	public Footer getFooter() {
		return footer;
	}

	public void setFooter(Footer footer) {
		this.footer = footer;
	}	
}
