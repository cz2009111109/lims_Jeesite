/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.project.entity.FsProject;
import com.thinkgem.jeesite.modules.project.entity.FsSubproject;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 提取检测任务Entity
 * @author chenzhe
 * @version 2019-08-16
 */
public class FsExtractionDetectionTask extends DataEntity<FsExtractionDetectionTask> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 提取检测任务单号
	private String customer;		// 客户
	private FsProject project;		// 总项目
	private FsSubproject subproject;		// 子项目
	private User chiefOfficer;		// 总负责人
	private User labOfficer;		// 实验负责人
	private User infomationOfficer;		// 信息负责人
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String file;		// 附件
	
	public FsExtractionDetectionTask() {
		super();
	}

	public FsExtractionDetectionTask(String id){
		super(id);
	}

	@Length(min=0, max=64, message="提取检测任务单号长度必须介于 0 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=64, message="客户长度必须介于 0 和 64 之间")
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public FsProject getProject() {
		return project;
	}

	public void setProject(FsProject project) {
		this.project = project;
	}
	
	public FsSubproject getSubproject() {
		return subproject;
	}

	public void setSubproject(FsSubproject subproject) {
		this.subproject = subproject;
	}
	
	public User getChiefOfficer() {
		return chiefOfficer;
	}

	public void setChiefOfficer(User chiefOfficer) {
		this.chiefOfficer = chiefOfficer;
	}
	
	public User getLabOfficer() {
		return labOfficer;
	}

	public void setLabOfficer(User labOfficer) {
		this.labOfficer = labOfficer;
	}
	
	public User getInfomationOfficer() {
		return infomationOfficer;
	}

	public void setInfomationOfficer(User infomationOfficer) {
		this.infomationOfficer = infomationOfficer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=10000, message="附件长度必须介于 0 和 10000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}


}