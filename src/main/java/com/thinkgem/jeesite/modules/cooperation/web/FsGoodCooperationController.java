/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cooperation.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.bank.entity.FsBank;
import com.thinkgem.jeesite.modules.bank.service.FsBankService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cooperation.entity.FsGoodCooperation;
import com.thinkgem.jeesite.modules.cooperation.service.FsGoodCooperationService;

import java.io.PipedReader;
import java.util.List;

/**
 * 试剂供应商Controller
 * @author chenzhe
 * @version 2019-08-08
 */
@Controller
@RequestMapping(value = "${adminPath}/cooperation/fsGoodCooperation")
public class FsGoodCooperationController extends BaseController {

	@Autowired
	private FsBankService fsBankService;

	@Autowired
	private FsGoodCooperationService fsGoodCooperationService;
	
	@ModelAttribute
	public FsGoodCooperation get(@RequestParam(required=false) String id) {
		FsGoodCooperation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsGoodCooperationService.get(id);
		}
		if (entity == null){
			entity = new FsGoodCooperation();
		}
		return entity;
	}
	
	@RequiresPermissions("cooperation:fsGoodCooperation:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsGoodCooperation fsGoodCooperation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsGoodCooperation> page = fsGoodCooperationService.findPage(new Page<FsGoodCooperation>(request, response), fsGoodCooperation); 
		model.addAttribute("page", page);
		return "modules/cooperation/fsGoodCooperationList";
	}

	@RequiresPermissions("cooperation:fsGoodCooperation:view")
	@RequestMapping(value = "form")
	public String form(FsGoodCooperation fsGoodCooperation, Model model) {
		FsBank fsBank=new FsBank();
		List<FsBank> fsBanks=fsBankService.findList(fsBank);
		model.addAttribute("fsBanks",fsBanks);
		model.addAttribute("fsGoodCooperation", fsGoodCooperation);
		return "modules/cooperation/fsGoodCooperationForm";
	}

	@RequiresPermissions("cooperation:fsGoodCooperation:edit")
	@RequestMapping(value = "save")
	public String save(FsGoodCooperation fsGoodCooperation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsGoodCooperation)){
			return form(fsGoodCooperation, model);
		}
		fsGoodCooperationService.save(fsGoodCooperation);
		addMessage(redirectAttributes, "保存试剂供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/cooperation/fsGoodCooperation/?repage";
	}
	
	@RequiresPermissions("cooperation:fsGoodCooperation:edit")
	@RequestMapping(value = "delete")
	public String delete(FsGoodCooperation fsGoodCooperation, RedirectAttributes redirectAttributes) {
		fsGoodCooperationService.delete(fsGoodCooperation);
		addMessage(redirectAttributes, "删除试剂供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/cooperation/fsGoodCooperation/?repage";
	}

}