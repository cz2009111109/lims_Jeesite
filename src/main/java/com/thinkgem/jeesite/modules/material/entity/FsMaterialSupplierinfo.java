/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.bank.entity.FsBank;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商信息Entity
 * @author chenzhe
 * @version 2019-08-26
 */
public class FsMaterialSupplierinfo extends DataEntity<FsMaterialSupplierinfo> {
	
	private static final long serialVersionUID = 1L;
	private String suppCode;		// 供应商编号
	private String name;		// 供货商名称
	private String suppAbbre;		// 简写
	private String personname;		// 供应商联系人
	private String mobilephonne;		// 手机电话
	private String telephone;		// 座机
	private String email;		// 邮箱
	private String address;		// 地址
	private FsBank bank;		// 银行账号
	
	public FsMaterialSupplierinfo() {
		super();
	}

	public FsMaterialSupplierinfo(String id){
		super(id);
	}

	@Length(min=0, max=50, message="供应商编号长度必须介于 0 和 50 之间")
	public String getSuppCode() {
		return suppCode;
	}

	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}
	
	@Length(min=0, max=200, message="供货商名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="简写长度必须介于 0 和 100 之间")
	public String getSuppAbbre() {
		return suppAbbre;
	}

	public void setSuppAbbre(String suppAbbre) {
		this.suppAbbre = suppAbbre;
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
	
	public FsBank getBank() {
		return bank;
	}

	public void setBank(FsBank bank) {
		this.bank = bank;
	}
	
}