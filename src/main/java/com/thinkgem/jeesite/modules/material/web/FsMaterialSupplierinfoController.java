/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.web;

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
import com.thinkgem.jeesite.modules.material.entity.FsMaterialSupplierinfo;
import com.thinkgem.jeesite.modules.material.service.FsMaterialSupplierinfoService;

import java.util.List;

/**
 * 供应商信息Controller
 * @author chenzhe
 * @version 2019-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/material/fsMaterialSupplierinfo")
public class FsMaterialSupplierinfoController extends BaseController {

	@Autowired
	private FsBankService fsBankService;

	@Autowired
	private FsMaterialSupplierinfoService fsMaterialSupplierinfoService;
	
	@ModelAttribute
	public FsMaterialSupplierinfo get(@RequestParam(required=false) String id) {
		FsMaterialSupplierinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsMaterialSupplierinfoService.get(id);
		}
		if (entity == null){
			entity = new FsMaterialSupplierinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("material:fsMaterialSupplierinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsMaterialSupplierinfo fsMaterialSupplierinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsMaterialSupplierinfo> page = fsMaterialSupplierinfoService.findPage(new Page<FsMaterialSupplierinfo>(request, response), fsMaterialSupplierinfo); 
		model.addAttribute("page", page);
		return "modules/material/fsMaterialSupplierinfoList";
	}

	@RequiresPermissions("material:fsMaterialSupplierinfo:view")
	@RequestMapping(value = "form")
	public String form(FsMaterialSupplierinfo fsMaterialSupplierinfo, Model model) {
		FsBank fsBank=new FsBank();
		List<FsBank> fsBanks=fsBankService.findList(fsBank);
		model.addAttribute("fsBanks",fsBanks);
		model.addAttribute("fsMaterialSupplierinfo", fsMaterialSupplierinfo);
		return "modules/material/fsMaterialSupplierinfoForm";
	}

	@RequiresPermissions("material:fsMaterialSupplierinfo:edit")
	@RequestMapping(value = "save")
	public String save(FsMaterialSupplierinfo fsMaterialSupplierinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsMaterialSupplierinfo)){
			return form(fsMaterialSupplierinfo, model);
		}
		fsMaterialSupplierinfoService.save(fsMaterialSupplierinfo);
		addMessage(redirectAttributes, "保存供应商信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
	}
	
	@RequiresPermissions("material:fsMaterialSupplierinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(FsMaterialSupplierinfo fsMaterialSupplierinfo, RedirectAttributes redirectAttributes) {
		fsMaterialSupplierinfoService.delete(fsMaterialSupplierinfo);
		addMessage(redirectAttributes, "删除供应商信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
	}

}