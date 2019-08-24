/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bank.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 开票银行账号Entity
 * @author chenzhe
 * @version 2019-08-07
 */
public class FsBank extends DataEntity<FsBank> {
	
	private static final long serialVersionUID = 1L;
	private String bankaccount;		// 银行账号
	private String company;		// 银行
	private String openBank;		// 发票抬头
	private String money;		// 消费金额
	
	public FsBank() {
		super();
	}

	public FsBank(String id){
		super(id);
	}

	@Length(min=0, max=200, message="银行账号长度必须介于 0 和 200 之间")
	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	
	@Length(min=0, max=200, message="银行长度必须介于 0 和 200 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Length(min=0, max=200, message="发票抬头长度必须介于 0 和 200 之间")
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}