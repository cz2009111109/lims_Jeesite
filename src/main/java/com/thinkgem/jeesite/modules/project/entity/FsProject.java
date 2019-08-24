/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.project.entity;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目管理Entity
 * @author chenzhe
 * @version 2019-08-15
 */
public class FsProject extends DataEntity<FsProject> {
	
	private static final long serialVersionUID = 1L;
	private String projectNumber;		// 项目编号
	private String projectName;		// 项目名称
	private String contractId;		// 合同id
	private String contractNumber;		// 合同编号
	private String contractName;		// 合同名称
	private User salesperson;		// 销售
	private String salespersonName;		// 销售名称
	private String platform;		// 测序平台
	private String projectType;		// 项目类型  HIC RNA DNA 二代 三代
	private String contractPrice;		// 合同金额
	private String receivingorderMoney;		// 回款金额
	private Integer projectCycle;		// 项目周期
	private User projectAdmin;		// 项目管理员
	private String projectState;		// 总项目状态
	private Date projectStateChangetime;		// 项目状态变更时间
	private Date projectSuccessTime;		// 结题日期
	private String money;		// 项目金额
	private String moneyAdvance;		// 提前款
	private String moneyMid;		// 中款
	private String moneyFinal;		// 尾款
	private Date arriveTime;		// 到样时间
	private Integer startInAdvance;		// 是否提前启动
	private Date startInAdvanceTime;		// 提起启动日期
	private Date pauseStart;		// 暂停开始时间
	private Date pauseEnd;		// 暂停停止时间
	private Date planStarttime;		// 计划开始时间
	private Date planEndtime;		// 计划结束时间
	private Date actualStarttime;		// 实际开始时间
	private Date actualEndtime;		// 实际结束时间
	private String projectfile;		// 附件
	
	public FsProject() {
		super();
	}

	public FsProject(String id){
		super(id);
	}

	@Length(min=0, max=60, message="项目编号长度必须介于 0 和 60 之间")
	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	@Length(min=0, max=200, message="项目名称长度必须介于 0 和 200 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=64, message="合同id长度必须介于 0 和 64 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Length(min=0, max=64, message="合同编号长度必须介于 0 和 64 之间")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
	@Length(min=0, max=300, message="合同名称长度必须介于 0 和 300 之间")
	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	
	public User getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(User salesperson) {
		this.salesperson = salesperson;
	}
	
	@Length(min=0, max=30, message="销售名称长度必须介于 0 和 30 之间")
	public String getSalespersonName() {
		return salespersonName;
	}

	public void setSalespersonName(String salespersonName) {
		this.salespersonName = salespersonName;
	}
	
	@Length(min=0, max=50, message="测序平台长度必须介于 0 和 50 之间")
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	@Length(min=0, max=300, message="项目类型  HIC RNA DNA 二代 三代长度必须介于 0 和 300 之间")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}
	
	public String getReceivingorderMoney() {
		return receivingorderMoney;
	}

	public void setReceivingorderMoney(String receivingorderMoney) {
		this.receivingorderMoney = receivingorderMoney;
	}
	
	public Integer getProjectCycle() {
		return projectCycle;
	}

	public void setProjectCycle(Integer projectCycle) {
		this.projectCycle = projectCycle;
	}
	
	public User getProjectAdmin() {
		return projectAdmin;
	}

	public void setProjectAdmin(User projectAdmin) {
		this.projectAdmin = projectAdmin;
	}
	
	@Length(min=0, max=20, message="总项目状态长度必须介于 0 和 20 之间")
	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProjectStateChangetime() {
		return projectStateChangetime;
	}

	public void setProjectStateChangetime(Date projectStateChangetime) {
		this.projectStateChangetime = projectStateChangetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProjectSuccessTime() {
		return projectSuccessTime;
	}

	public void setProjectSuccessTime(Date projectSuccessTime) {
		this.projectSuccessTime = projectSuccessTime;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	public String getMoneyAdvance() {
		return moneyAdvance;
	}

	public void setMoneyAdvance(String moneyAdvance) {
		this.moneyAdvance = moneyAdvance;
	}
	
	public String getMoneyMid() {
		return moneyMid;
	}

	public void setMoneyMid(String moneyMid) {
		this.moneyMid = moneyMid;
	}
	
	public String getMoneyFinal() {
		return moneyFinal;
	}

	public void setMoneyFinal(String moneyFinal) {
		this.moneyFinal = moneyFinal;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	public Integer getStartInAdvance() {
		return startInAdvance;
	}

	public void setStartInAdvance(Integer startInAdvance) {
		this.startInAdvance = startInAdvance;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartInAdvanceTime() {
		return startInAdvanceTime;
	}

	public void setStartInAdvanceTime(Date startInAdvanceTime) {
		this.startInAdvanceTime = startInAdvanceTime;
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
	public String getProjectfile() {
		return projectfile;
	}

	public void setProjectfile(String projectfile) {
		this.projectfile = projectfile;
	}

	public List<String> getProjectTypeList() {
		List<String> list = Lists.newArrayList();
		if (projectType != null){
			for (String s : StringUtils.split(projectType, ",")) {
				list.add(s);
			}
		}
		return list;
	}

	public List<String> getPlatformList() {
		List<String> list = Lists.newArrayList();
		if (platform != null){
			for (String s : StringUtils.split(platform, ",")) {
				list.add(s);
			}
		}
		return list;
	}
}