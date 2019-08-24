/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.project.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.project.entity.FsProject;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 子项目管理Entity
 * @author chenzhe
 * @version 2019-08-15
 */
public class FsSubproject extends DataEntity<FsSubproject> {
	
	private static final long serialVersionUID = 1L;
	private String subprojectNumber;		// 子项目编号
	private String subprojectName;		// 子项目名称
	private String contractid;		// 合同
	private FsProject fsProject;		// 总项目
	private String salesperson;		// 销售id
	private String platform;		// 测序平台
	private String subprojectType;		// 子项目类型
	private String subprojectPrice;		// 子项目费用
	private String receivingorderMonney;		// 回款金额
	private String subprojectCycle;		// 子项目周期
	private User limsUserOne;		// lims系统用户1
	private User limsUserTwo;		// lims系统用户2
	private User subprojectAdmin;		// 子项目管理
	private String subprojectState;		// 子项目状态
	private Date pauseStart;		// 暂停开始时间
	private Date pauseEnd;		// 暂停停止时间
	private Date planStarttime;		// 计划开始时间
	private Date planEndtime;		// 计划结束时间
	private Date actualStarttime;		// 实际开始时间
	private Date actualEndtime;		// 实际结束时间
	private String subproject;		// 附件
	
	public FsSubproject() {
		super();
	}

	public FsSubproject(String id){
		super(id);
	}

	@Length(min=0, max=100, message="子项目编号长度必须介于 0 和 100 之间")
	public String getSubprojectNumber() {
		return subprojectNumber;
	}

	public void setSubprojectNumber(String subprojectNumber) {
		this.subprojectNumber = subprojectNumber;
	}
	
	@Length(min=0, max=500, message="子项目名称长度必须介于 0 和 500 之间")
	public String getSubprojectName() {
		return subprojectName;
	}

	public void setSubprojectName(String subprojectName) {
		this.subprojectName = subprojectName;
	}
	
	@Length(min=0, max=64, message="合同长度必须介于 0 和 64 之间")
	public String getContractid() {
		return contractid;
	}

	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	
	public FsProject getFsProject() {
		return fsProject;
	}

	public void setFsProject(FsProject fsProject) {
		this.fsProject = fsProject;
	}
	
	@Length(min=0, max=64, message="销售id长度必须介于 0 和 64 之间")
	public String getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(String salesperson) {
		this.salesperson = salesperson;
	}
	
	@Length(min=0, max=200, message="测序平台长度必须介于 0 和 200 之间")
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	@Length(min=0, max=300, message="子项目类型长度必须介于 0 和 300 之间")
	public String getSubprojectType() {
		return subprojectType;
	}

	public void setSubprojectType(String subprojectType) {
		this.subprojectType = subprojectType;
	}
	
	public String getSubprojectPrice() {
		return subprojectPrice;
	}

	public void setSubprojectPrice(String subprojectPrice) {
		this.subprojectPrice = subprojectPrice;
	}
	
	public String getReceivingorderMonney() {
		return receivingorderMonney;
	}

	public void setReceivingorderMonney(String receivingorderMonney) {
		this.receivingorderMonney = receivingorderMonney;
	}
	
	@Length(min=0, max=64, message="子项目周期长度必须介于 0 和 64 之间")
	public String getSubprojectCycle() {
		return subprojectCycle;
	}

	public void setSubprojectCycle(String subprojectCycle) {
		this.subprojectCycle = subprojectCycle;
	}
	
	public User getLimsUserOne() {
		return limsUserOne;
	}

	public void setLimsUserOne(User limsUserOne) {
		this.limsUserOne = limsUserOne;
	}
	
	public User getLimsUserTwo() {
		return limsUserTwo;
	}

	public void setLimsUserTwo(User limsUserTwo) {
		this.limsUserTwo = limsUserTwo;
	}
	
	public User getSubprojectAdmin() {
		return subprojectAdmin;
	}

	public void setSubprojectAdmin(User subprojectAdmin) {
		this.subprojectAdmin = subprojectAdmin;
	}
	
	@Length(min=0, max=20, message="子项目状态长度必须介于 0 和 20 之间")
	public String getSubprojectState() {
		return subprojectState;
	}

	public void setSubprojectState(String subprojectState) {
		this.subprojectState = subprojectState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPauseStart() {
		return pauseStart;
	}

	public void setPauseStart(Date pauseStart) {
		this.pauseStart = pauseStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPauseEnd() {
		return pauseEnd;
	}

	public void setPauseEnd(Date pauseEnd) {
		this.pauseEnd = pauseEnd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanStarttime() {
		return planStarttime;
	}

	public void setPlanStarttime(Date planStarttime) {
		this.planStarttime = planStarttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanEndtime() {
		return planEndtime;
	}

	public void setPlanEndtime(Date planEndtime) {
		this.planEndtime = planEndtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualStarttime() {
		return actualStarttime;
	}

	public void setActualStarttime(Date actualStarttime) {
		this.actualStarttime = actualStarttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualEndtime() {
		return actualEndtime;
	}

	public void setActualEndtime(Date actualEndtime) {
		this.actualEndtime = actualEndtime;
	}
	
	@Length(min=0, max=10000, message="附件长度必须介于 0 和 10000 之间")
	public String getSubproject() {
		return subproject;
	}

	public void setSubproject(String subproject) {
		this.subproject = subproject;
	}
	
}