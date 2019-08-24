/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.storehouse.entity.FsStorehouse;
import com.thinkgem.jeesite.modules.storehouse.service.FsStorehouseService;
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
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimenin;
import com.thinkgem.jeesite.modules.specimen.service.FsSpecimeninService;

import java.util.Arrays;
import java.util.List;

/**
 * 样本入库Controller
 * @author chenzhe
 * @version 2019-08-12
 */
@Controller
@RequestMapping(value = "${adminPath}/specimen/fsSpecimenin")
public class FsSpecimeninController extends BaseController {

	@Autowired
	private FsStorehouseService fsStorehouseService;

	@Autowired
	private FsSpecimeninService fsSpecimeninService;
	
	@ModelAttribute
	public FsSpecimenin get(@RequestParam(required=false) String id) {
		FsSpecimenin entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsSpecimeninService.get(id);
		}
		if (entity == null){
			entity = new FsSpecimenin();
		}
		return entity;
	}
	
	@RequiresPermissions("specimen:fsSpecimenin:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsSpecimenin fsSpecimenin, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsSpecimenin> page = fsSpecimeninService.findPage(new Page<FsSpecimenin>(request, response), fsSpecimenin); 
		model.addAttribute("page", page);
		return "modules/specimen/fsSpecimeninList";
	}

	@RequiresPermissions("specimen:fsSpecimenin:view")
	@RequestMapping(value = "form")
	public String form(FsSpecimenin fsSpecimenin, Model model) {
		FsStorehouse fsStorehouse=new FsStorehouse();
		List<FsStorehouse> storehouses=fsStorehouseService.findList(fsStorehouse);
		model.addAttribute("storehouses", storehouses);
		model.addAttribute("fsSpecimenin", fsSpecimenin);
		return "modules/specimen/fsSpecimeninForm";
	}

	@RequiresPermissions("specimen:fsSpecimenin:edit")
	@RequestMapping(value = "save")
	public String save(FsSpecimenin fsSpecimenin, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsSpecimenin)){
			return form(fsSpecimenin, model);
		}
		fsSpecimeninService.save(fsSpecimenin);
		addMessage(redirectAttributes, "保存样本入库成功");
		return "redirect:"+Global.getAdminPath()+"/specimen/fsSpecimenin/?repage";
	}
	
	@RequiresPermissions("specimen:fsSpecimenin:edit")
	@RequestMapping(value = "delete")
	public String delete(FsSpecimenin fsSpecimenin, RedirectAttributes redirectAttributes) {
		fsSpecimeninService.delete(fsSpecimenin);
		addMessage(redirectAttributes, "删除样本入库成功");
		return "redirect:"+Global.getAdminPath()+"/specimen/fsSpecimenin/?repage";
	}

}