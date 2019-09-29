/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.thinkgem.jeesite.modules.material.dao.FsMaterialPurchasingorderlistInDao;
import com.thinkgem.jeesite.modules.material.dao.FsMaterialPurchasingorderlistStockDao;
import com.thinkgem.jeesite.modules.material.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.material.dao.FsMaterialPurchasingorderlistDao;

/**
 * 申购单货物Service
 * @author chenzhe
 * @version 2019-08-28
 */
@Service
@Transactional(readOnly = true )
public class FsMaterialPurchasingorderlistService extends CrudService<FsMaterialPurchasingorderlistDao, FsMaterialPurchasingorderlist> {

	@Autowired
	private FsMaterialPurchasingorderlistInDao fsMaterialPurchasingorderlistInDao;

	@Autowired
	private FsMaterialPurchasingorderlistStockDao fsMaterialPurchasingorderlistStockDao;

	@Override
	public FsMaterialPurchasingorderlist get(String id) {
		return super.get(id);
	}

	public FsMaterialPurchasingorderlist getObjectIn(String id){
		FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist=super.get(id);
		fsMaterialPurchasingorderlist.setFsMaterialPurchasingorderlistIns(fsMaterialPurchasingorderlistInDao.findList(new FsMaterialPurchasingorderlistIn(fsMaterialPurchasingorderlist)));
		return fsMaterialPurchasingorderlist;
	}

	@Autowired
	private FsMaterialPurchasingorderService fsMaterialPurchasingorderService;

	@Autowired
	private FsMaterialPurchasingorderlistInService fsMaterialPurchasingorderlistInService;

	@Autowired
	private FsMaterialPurchasingorderlistStockService fsMaterialPurchasingorderlistStockService;

	@Override
	public List<FsMaterialPurchasingorderlist> findList(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist) {
		return super.findList(fsMaterialPurchasingorderlist);
	}

	@Override
	public Page<FsMaterialPurchasingorderlist> findPage(Page<FsMaterialPurchasingorderlist> page, FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist) {
		return super.findPage(page, fsMaterialPurchasingorderlist);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist) {
		super.saveMaxId(fsMaterialPurchasingorderlist);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist) {
		super.delete(fsMaterialPurchasingorderlist);
	}

	@Transactional(readOnly = false)
	public String objectIn(FsMaterialPurchasingorderlistParam fsMaterialPurchasingorderlistParam){
		FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist=get(fsMaterialPurchasingorderlistParam.getId());
		FsMaterialPurchasingorderlistIn fsMaterialPurchasingorderlistIn=new FsMaterialPurchasingorderlistIn();
		BeanUtils.copyProperties(fsMaterialPurchasingorderlist,fsMaterialPurchasingorderlistIn);
		fsMaterialPurchasingorderlistIn.setWarehouseinfo(new FsMaterialWarehouseinfo(fsMaterialPurchasingorderlistParam.getWarehouseinfo().getId()));
		if(fsMaterialPurchasingorderlistParam.getObjectIn().doubleValue()>fsMaterialPurchasingorderlist.getSurplusNum().doubleValue()){
			fsMaterialPurchasingorderlistParam.setObjectIn(fsMaterialPurchasingorderlist.getSurplusNum());
		}
		fsMaterialPurchasingorderlist.setSurplusNum(fsMaterialPurchasingorderlist.getSurplusNum().subtract(fsMaterialPurchasingorderlistParam.getObjectIn()));
		if(fsMaterialPurchasingorderlist.getSurplusNum().compareTo(BigDecimal.ZERO)==0){
			fsMaterialPurchasingorderlist.setEntryStatus(3);
			fsMaterialPurchasingorderlist.setPurchStatus(2);
		}
		fsMaterialPurchasingorderlistIn.setOrderlist(fsMaterialPurchasingorderlist);
		fsMaterialPurchasingorderlistIn.setObjectinNum(fsMaterialPurchasingorderlistParam.getObjectIn());
		String id=String.valueOf(fsMaterialPurchasingorderlistInDao.getMaxid()!=null?fsMaterialPurchasingorderlistInDao.getMaxid():1L) ;
		fsMaterialPurchasingorderlistIn.setId("");
		fsMaterialPurchasingorderlistIn.preInsertMaxId(id);
		if(fsMaterialPurchasingorderlistIn.getObjectinNum().compareTo(BigDecimal.ZERO)>0){
			FsMaterialPurchasingorderlistStock fsMaterialPurchasingorderlistStock=new FsMaterialPurchasingorderlistStock();
			BeanUtils.copyProperties(fsMaterialPurchasingorderlistIn,fsMaterialPurchasingorderlistStock);
			fsMaterialPurchasingorderlistStock.setStockNum(fsMaterialPurchasingorderlistIn.getObjectinNum());
			fsMaterialPurchasingorderlistStock.setStockin(fsMaterialPurchasingorderlistIn);
			id=String.valueOf(fsMaterialPurchasingorderlistStockDao.getMaxid()!=null?fsMaterialPurchasingorderlistStockDao.getMaxid():1L) ;
			fsMaterialPurchasingorderlistStock.setId("");
			fsMaterialPurchasingorderlistStock.preInsertMaxId(id);
			fsMaterialPurchasingorderlistStock.setStockStatus(1);
			fsMaterialPurchasingorderlistStockDao.insert(fsMaterialPurchasingorderlistStock);
		}

		fsMaterialPurchasingorderlistInDao.insert(fsMaterialPurchasingorderlistIn);
		fsMaterialPurchasingorderlist.preUpdate();
		dao.updateObjectIn(fsMaterialPurchasingorderlist);
		return "申购单号"+fsMaterialPurchasingorderlist.getOrderId().getOrderCode()+"入库数量"+fsMaterialPurchasingorderlistParam.getObjectIn().doubleValue()+"成功";
	}

	@Transactional(readOnly = false)
	public String payment(FsMaterialPurchasingorderlistParam fsMaterialPurchasingorderlistParam){
		FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist=get(fsMaterialPurchasingorderlistParam.getId());
		fsMaterialPurchasingorderlist.setPayStatus(2);
		dao.payInvoice(fsMaterialPurchasingorderlist);
		return "申购单号"+fsMaterialPurchasingorderlist.getOrderId().getOrderCode()+"更新成功";
	}

	@Transactional(readOnly = false)
	public String invoice(FsMaterialPurchasingorderlistParam fsMaterialPurchasingorderlistParam){
		FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist=get(fsMaterialPurchasingorderlistParam.getId());
		fsMaterialPurchasingorderlist.setInvoiceStatus(2);
		dao.payInvoice(fsMaterialPurchasingorderlist);
		return "申购单号"+fsMaterialPurchasingorderlist.getOrderId().getOrderCode()+"更新成功";
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist) {
		return	super.insert(fsMaterialPurchasingorderlist);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist) {
		return	super.update(fsMaterialPurchasingorderlist);
	}

	public List<FsMaterialPurchasingorderlist> findByIds(String[] ids){
		return  dao.findByIds(ids);
	}

	public List<FsMaterialPurchasingorderlist> findByIds(Set<String> ids){
		return  dao.findByIdsSet(ids);
	}

	public List<FsMaterialPurchasingorderlist> findByIds(List<String> ids){
		return  dao.findByIdsList(ids);
	}
}