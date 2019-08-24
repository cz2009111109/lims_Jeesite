/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cooperation.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.bank.entity.FsBank;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 试剂供应商Entity
 * @author chenzhe
 * @version 2019-08-08
 */
public class FsGoodCooperation extends DataEntity<FsGoodCooperation> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 供货商名称
	private String personname;		// 供应商联系人
	private String mobilephonne;		// 手机电话
	private String telephone;		// 座机
	private String email;		// 邮箱
	private String address;		// 地址
	private FsBank fsBank;		// 银行账号
	
	public FsGoodCooperation() {
		super();
	}

	public FsGoodCooperation(String id){
		super(id);
	}

	@Length(min=0, max=200, message="供货商名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="供应商联系人长度必须介于 0 和 20 之间")
	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}
	
	@Length(min=0, max=20, message="手机电话长度必须介于 0 和 20 之间")
	public String getMobilephonne() {
		return mobilephonne;
	}

	public void setMobilephonne(String mobilephonne) {
		this.mobilephonne = mobilephonne;
	}
	
	@Length(min=0, max=20, message="座机长度必须介于 0 和 20 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public FsBank getFsBank() {
		return fsBank;
	}

	public void setFsBank(FsBank fsBank) {
		this.fsBank = fsBank;
	}
	
}