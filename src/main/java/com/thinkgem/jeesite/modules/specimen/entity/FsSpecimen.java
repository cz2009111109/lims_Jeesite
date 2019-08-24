/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.entity;

import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimenin;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;
import com.thinkgem.jeesite.modules.storehouse.entity.FsStorehouse;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 样本库存Entity
 * @author chenzhe
 * @version 2019-08-13
 */
public class FsSpecimen extends DataEntity<FsSpecimen> {
	
	private static final long serialVersionUID = 1L;
	private Long contractid;		// 合同id(来自CRM)
	private FsSpecimenin fsSpecimenin;		// 入库样本
	private String contractnumber;		// 合同编号
	private String contractname;		// 合同名称
	private Long customerId;		// 客户id
	private String customerName;		// 客户名称
	private String customerEmail;		// 客户邮箱
	private String customerPhone;		// 客户手机
	private String sampleType;		// 样本类型 RNA DNA 组织样三种
	private String samplingName;		// 送样人姓名
	private String samplingPhone;		// 送样人电话
	private String samplingEmail;		// 送样人邮箱
	private String samplingState;		// 送样状态（干冰 常温）
	private String sampleSpecies;		// 样本类型(动物 植物 细菌 真菌 其它等)
	private String sampleTarget;		// 样本目的
	private String samplingBeforestate;		// 采样前状态
	private String sampleSerialnumber;		// 样本编号（组织编号）
	private String sampleName;		// 样本名称(或组织名称)
	private String speciesName;		// 物种名称(如蓝藻 牛)
	private String projectType;		// 项目类型 HIC RNA DNA
	private BigDecimal tubes;		// 数量(默认管数)
	private String unit;		// 数量单位（管，只，块）
	private String param1;		// 参数1 组织部位  Nanodrop ng/uL
	private String param2;		// 参数2 组织量 Qubit ng/ul  RIN
	private String param3;		// 参数3 体积 uL  提取说明
	private String param4;		// 参数4 溶解状态  组织样品状态
	private String param5;		// 是否经过DNase处理
	private String param6;		// param6
	private String param7;		// param7
	private String param8;		// 测序平台
	private String param9;		// param9
	private String param10;		// param10
	private Integer state;		// 样本库存状态(0表示未用完,1表示用完)
	private String extractionDescription;		// 提取说明
	private FsStorehouse fsStorehouse;		// 存储位置
	private User user;		// 收样人
	
	public FsSpecimen() {
		super();
	}

	public FsSpecimen(String id){
		super(id);
	}

	public Long getContractid() {
		return contractid;
	}

	public void setContractid(Long contractid) {
		this.contractid = contractid;
	}
	
	public FsSpecimenin getFsSpecimenin() {
		return fsSpecimenin;
	}

	public void setFsSpecimenin(FsSpecimenin fsSpecimenin) {
		this.fsSpecimenin = fsSpecimenin;
	}
	
	@Length(min=0, max=50, message="合同编号长度必须介于 0 和 50 之间")
	public String getContractnumber() {
		return contractnumber;
	}

	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}
	
	@Length(min=0, max=200, message="合同名称长度必须介于 0 和 200 之间")
	public String getContractname() {
		return contractname;
	}

	public void setContractname(String contractname) {
		this.contractname = contractname;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Length(min=0, max=20, message="客户名称长度必须介于 0 和 20 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=100, message="客户邮箱长度必须介于 0 和 100 之间")
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	@Length(min=0, max=20, message="客户手机长度必须介于 0 和 20 之间")
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Length(min=0, max=20, message="样本类型 RNA DNA 组织样三种长度必须介于 0 和 20 之间")
	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	
	@Length(min=0, max=20, message="送样人姓名长度必须介于 0 和 20 之间")
	public String getSamplingName() {
		return samplingName;
	}

	public void setSamplingName(String samplingName) {
		this.samplingName = samplingName;
	}
	
	@Length(min=0, max=20, message="送样人电话长度必须介于 0 和 20 之间")
	public String getSamplingPhone() {
		return samplingPhone;
	}

	public void setSamplingPhone(String samplingPhone) {
		this.samplingPhone = samplingPhone;
	}
	
	@Length(min=0, max=100, message="送样人邮箱长度必须介于 0 和 100 之间")
	public String getSamplingEmail() {
		return samplingEmail;
	}

	public void setSamplingEmail(String samplingEmail) {
		this.samplingEmail = samplingEmail;
	}
	
	@Length(min=0, max=10, message="送样状态（干冰 常温）长度必须介于 0 和 10 之间")
	public String getSamplingState() {
		return samplingState;
	}

	public void setSamplingState(String samplingState) {
		this.samplingState = samplingState;
	}
	
	@Length(min=0, max=10, message="样本类型(动物 植物 细菌 真菌 其它等)长度必须介于 0 和 10 之间")
	public String getSampleSpecies() {
		return sampleSpecies;
	}

	public void setSampleSpecies(String sampleSpecies) {
		this.sampleSpecies = sampleSpecies;
	}
	
	@Length(min=0, max=200, message="样本目的长度必须介于 0 和 200 之间")
	public String getSampleTarget() {
		return sampleTarget;
	}

	public void setSampleTarget(String sampleTarget) {
		this.sampleTarget = sampleTarget;
	}
	
	@Length(min=0, max=50, message="采样前状态长度必须介于 0 和 50 之间")
	public String getSamplingBeforestate() {
		return samplingBeforestate;
	}

	public void setSamplingBeforestate(String samplingBeforestate) {
		this.samplingBeforestate = samplingBeforestate;
	}
	
	@Length(min=0, max=30, message="样本编号（组织编号）长度必须介于 0 和 30 之间")
	public String getSampleSerialnumber() {
		return sampleSerialnumber;
	}

	public void setSampleSerialnumber(String sampleSerialnumber) {
		this.sampleSerialnumber = sampleSerialnumber;
	}
	
	@Length(min=0, max=50, message="样本名称(或组织名称)长度必须介于 0 和 50 之间")
	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	
	@Length(min=0, max=50, message="物种名称(如蓝藻 牛)长度必须介于 0 和 50 之间")
	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}
	
	@Length(min=0, max=300, message="项目类型 HIC RNA DNA长度必须介于 0 和 300 之间")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public BigDecimal getTubes() {
		return tubes;
	}

	public void setTubes(BigDecimal tubes) {
		this.tubes = tubes;
	}
	
	@Length(min=0, max=10, message="数量单位（管，只，块）长度必须介于 0 和 10 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=200, message="参数1 组织部位  Nanodrop ng/uL长度必须介于 0 和 200 之间")
	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}
	
	@Length(min=0, max=200, message="参数2 组织量 Qubit ng/ul  RIN长度必须介于 0 和 200 之间")
	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}
	
	@Length(min=0, max=200, message="参数3 体积 uL  提取说明长度必须介于 0 和 200 之间")
	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}
	
	@Length(min=0, max=200, message="参数4 溶解状态  组织样品状态长度必须介于 0 和 200 之间")
	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}
	
	@Length(min=0, max=200, message="是否经过DNase处理长度必须介于 0 和 200 之间")
	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}
	
	@Length(min=0, max=200, message="param6长度必须介于 0 和 200 之间")
	public String getParam6() {
		return param6;
	}

	public void setParam6(String param6) {
		this.param6 = param6;
	}
	
	@Length(min=0, max=200, message="param7长度必须介于 0 和 200 之间")
	public String getParam7() {
		return param7;
	}

	public void setParam7(String param7) {
		this.param7 = param7;
	}
	
	@Length(min=0, max=200, message="测序平台长度必须介于 0 和 200 之间")
	public String getParam8() {
		return param8;
	}

	public void setParam8(String param8) {
		this.param8 = param8;
	}
	
	@Length(min=0, max=200, message="param9长度必须介于 0 和 200 之间")
	public String getParam9() {
		return param9;
	}

	public void setParam9(String param9) {
		this.param9 = param9;
	}
	
	@Length(min=0, max=200, message="param10长度必须介于 0 和 200 之间")
	public String getParam10() {
		return param10;
	}

	public void setParam10(String param10) {
		this.param10 = param10;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@Length(min=0, max=200, message="提取说明长度必须介于 0 和 200 之间")
	public String getExtractionDescription() {
		return extractionDescription;
	}

	public void setExtractionDescription(String extractionDescription) {
		this.extractionDescription = extractionDescription;
	}
	
	public FsStorehouse getFsStorehouse() {
		return fsStorehouse;
	}

	public void setFsStorehouse(FsStorehouse fsStorehouse) {
		this.fsStorehouse = fsStorehouse;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}