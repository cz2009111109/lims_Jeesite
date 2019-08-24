/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimen;
import com.thinkgem.jeesite.modules.specimen.service.FsSpecimenService;

/**
 * 样本库存Controller
 * @author chenzhe
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/specimen/fsSpecimen")
public class FsSpecimenController extends BaseController {

	@Autowired
	private FsSpecimenService fsSpecimenService;
	
	@ModelAttribute
	public FsSpecimen get(@RequestParam(required=false) String id) {
		FsSpecimen entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsSpecimenService.get(id);
		}
		if (entity == null){
			entity = new FsSpecimen();
		}
		return entity;
	}
	
	@RequiresPermissions("specimen:fsSpecimen:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsSpecimen fsSpecimen, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsSpecimen> page = fsSpecimenService.findPage(new Page<FsSpecimen>(request, response), fsSpecimen); 
		model.addAttribute("page", page);
		return "modules/specimen/fsSpecimenList";
	}

	@RequiresPermissions("specimen:fsSpecimen:view")
	@RequestMapping(value = "form")
	public String form(FsSpecimen fsSpecimen, Model model) {
		model.addAttribute("fsSpecimen", fsSpecimen);
		return "modules/specimen/fsSpecimenForm";
	}

	@RequiresPermissions("specimen:fsSpecimen:edit")
	@RequestMapping(value = "save")
	public String save(FsSpecimen fsSpecimen, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsSpecimen)){
			return form(fsSpecimen, model);
		}
		fsSpecimenService.save(fsSpecimen);
		addMessage(redirectAttributes, "保存样本库存成功");
		return "redirect:"+Global.getAdminPath()+"/specimen/fsSpecimen/?repage";
	}
	
	@RequiresPermissions("specimen:fsSpecimen:edit")
	@RequestMapping(value = "delete")
	public String delete(FsSpecimen fsSpecimen, RedirectAttributes redirectAttributes) {
		fsSpecimenService.delete(fsSpecimen);
		addMessage(redirectAttributes, "删除样本库存成功");
		return "redirect:"+Global.getAdminPath()+"/specimen/fsSpecimen/?repage";
	}

}