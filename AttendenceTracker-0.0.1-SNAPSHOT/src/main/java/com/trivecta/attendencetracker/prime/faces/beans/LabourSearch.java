package com.trivecta.attendencetracker.prime.faces.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.trivecta.attendencetracker.model.LabourSearchVO;
import com.trivecta.attendencetracker.model.LabourVO;
import com.trivecta.attendencetracker.model.transformer.CommonTransformer;
import com.trivecta.attendencetracker.model.transformer.LabourTransformer;

@ManagedBean
@SessionScoped
public class LabourSearch {
	
	@ManagedProperty("#{labourTransformer}")
	private LabourTransformer labourTransformer;
	
	@ManagedProperty("#{commonTransformer}")
	private CommonTransformer commonTransformer;
	
	@ManagedProperty("#{labourViewEdit}")
	private LabourViewEdit labourViewEdit;
	
	private LabourSearchVO labourSearchVO;
	
	private List<LabourSearchVO> labourSearchVOList;
	
	private LabourSearchVO selectedLabourVO;
	
	private List<SelectItem> cityVOs;
	
	public void init() {
		labourSearchVO = new LabourSearchVO();
		labourSearchVOList = new ArrayList<LabourSearchVO>();
		cityVOs = commonTransformer.getAllCities();
	}
	
	public void searchLabour() {
		labourSearchVOList = 
				labourTransformer.getLabourDetails(labourSearchVO);
	}
	
	public String viewLabour() {
		labourViewEdit.initViewEdit(selectedLabourVO.getId());
		return "/labour/view";
	}

	public LabourTransformer getLabourTransformer() {
		return labourTransformer;
	}

	public void setLabourTransformer(LabourTransformer labourTransformer) {
		this.labourTransformer = labourTransformer;
	}

	public LabourViewEdit getLabourViewEdit() {
		return labourViewEdit;
	}

	public void setLabourViewEdit(LabourViewEdit labourViewEdit) {
		this.labourViewEdit = labourViewEdit;
	}

	public LabourSearchVO getLabourSearchVO() {
		return labourSearchVO;
	}

	public void setLabourSearchVO(LabourSearchVO labourSearchVO) {
		this.labourSearchVO = labourSearchVO;
	}

	public List<LabourSearchVO> getLabourSearchVOList() {
		return labourSearchVOList;
	}

	public void setLabourSearchVOList(List<LabourSearchVO> labourSearchVOList) {
		this.labourSearchVOList = labourSearchVOList;
	}

	public LabourSearchVO getSelectedLabourVO() {
		return selectedLabourVO;
	}

	public void setSelectedLabourVO(LabourSearchVO selectedLabourVO) {
		this.selectedLabourVO = selectedLabourVO;
	}

	public CommonTransformer getCommonTransformer() {
		return commonTransformer;
	}

	public void setCommonTransformer(CommonTransformer commonTransformer) {
		this.commonTransformer = commonTransformer;
	}

	public List<SelectItem> getCityVOs() {
		return cityVOs;
	}

	public void setCityVOs(List<SelectItem> cityVOs) {
		this.cityVOs = cityVOs;
	}
}
