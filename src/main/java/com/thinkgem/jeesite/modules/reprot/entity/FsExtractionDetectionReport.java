/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reprot.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.project.entity.FsProject;
import com.thinkgem.jeesite.modules.project.entity.FsSubproject;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.task.entity.FsExtractionDetectionTask;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 提取检测报告Entity
 * @author chenzhe
 * @version 2019-08-16
 */
public class FsExtractionDetectionReport extends DataEntity<FsExtractionDetectionReport> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 提取检测任务单号
	private String customer;		// 客户
	private FsProject project;		// 总项目
	private FsSubproject subproject;		// 子项目
	private User chiefOfficer;		// 总负责人
	private User labOfficer;		// 实验负责人
	private User infomationOfficer;		// 信息负责人
	private FsExtractionDetectionTask extractionDetection;		// 提取检测任务
	private User receiveUser;		// 收样人
	private Date receiveTime;		// 收样日期
	private String samplingState;		// 运输情况
	private String receiveState;		// 到样状态
	private User auditor;		// 审核人
	private Date auditTime;		// 审核时间
	private String detectionSolution;		// 检测溶液
	private String detectionType;		// 检测内容
	private String tubes;		// 样品管数
	private String sampleType;		// 样本类型
	private Integer sampleNumber;		// 样品数
	private Integer passnumber;		// 合格数
	private Integer risknumber;		// 风险数
	private Integer failnumber;		// 不合格数
	private String informationCoincide;		// 与信息单是否相符
	private String file;		// 附件
	
	public FsExtractionDetectionReport() {
		super();
	}

	public FsExtractionDetectionReport(String id){
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
	
	public FsExtractionDetectionTask getExtractionDetection() {
		return extractionDetection;
	}

	public void setExtractionDetection(FsExtractionDetectionTask extractionDetection) {
		this.extractionDetection = extractionDetection;
	}
	
	public User getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(User receiveUser) {
		this.receiveUser = receiveUser;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	@Length(min=0, max=200, message="运输情况长度必须介于 0 和 200 之间")
	public String getSamplingState() {
		return samplingState;
	}

	public void setSamplingState(String samplingState) {
		this.samplingState = samplingState;
	}
	
	@Length(min=0, max=200, message="到样状态长度必须介于 0 和 200 之间")
	public String getReceiveState() {
		return receiveState;
	}

	public void setReceiveState(String receiveState) {
		this.receiveState = receiveState;
	}
	
	public User getAuditor() {
		return auditor;
	}

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	@Length(min=0, max=500, message="检测溶液长度必须介于 0 和 500 之间")
	public String getDetectionSolution() {
		return detectionSolution;
	}

	public void setDetectionSolution(String detectionSolution) {
		this.detectionSolution = detectionSolution;
	}
	
	@Length(min=0, max=500, message="检测内容长度必须介于 0 和 500 之间")
	public String getDetectionType() {
		return detectionType;
	}

	public void setDetectionType(String detectionType) {
		this.detectionType = detectionType;
	}
	
	public String getTubes() {
		return tubes;
	}

	public void setTubes(String tubes) {
		this.tubes = tubes;
	}
	
	@Length(min=0, max=64, message="样本类型长度必须介于 0 和 64 之间")
	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	
	public Integer getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(Integer sampleNumber) {
		this.sampleNumber = sampleNumber;
	}
	
	public Integer getPassnumber() {
		return passnumber;
	}

	public void setPassnumber(Integer passnumber) {
		this.passnumber = passnumber;
	}
	
	public Integer getRisknumber() {
		return risknumber;
	}

	public void setRisknumber(Integer risknumber) {
		this.risknumber = risknumber;
	}
	
	public Integer getFailnumber() {
		return failnumber;
	}

	public void setFailnumber(Integer failnumber) {
		this.failnumber = failnumber;
	}
	
	@Length(min=0, max=10, message="与信息单是否相符长度必须介于 0 和 10 之间")
	public String getInformationCoincide() {
		return informationCoincide;
	}

	public void setInformationCoincide(String informationCoincide) {
		this.informationCoincide = informationCoincide;
	}
	
	@Length(min=0, max=10000, message="附件长度必须介于 0 和 10000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}