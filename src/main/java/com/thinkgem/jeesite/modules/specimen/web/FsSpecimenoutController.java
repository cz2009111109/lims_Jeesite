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
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimenout;
import com.thinkgem.jeesite.modules.specimen.service.FsSpecimenoutService;

/**
 * 样本出库Controller
 * @author chenzhe
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/specimen/fsSpecimenout")
public class FsSpecimenoutController extends BaseController {

	@Autowired
	private FsSpecimenoutService fsSpecimenoutService;
	
	@ModelAttribute
	public FsSpecimenout get(@RequestParam(required=false) String id) {
		FsSpecimenout entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsSpecimenoutService.get(id);
		}
		if (entity == null){
			entity = new FsSpecimenout();
		}
		return entity;
	}
	
	@RequiresPermissions("specimen:fsSpecimenout:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsSpecimenout fsSpecimenout, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsSpecimenout> page = fsSpecimenoutService.findPage(new Page<FsSpecimenout>(request, response), fsSpecimenout); 
		model.addAttribute("page", page);
		return "modules/specimen/fsSpecimenoutList";
	}

	@RequiresPermissions("specimen:fsSpecimenout:view")
	@RequestMapping(value = "form")
	public String form(FsSpecimenout fsSpecimenout, Model model) {
		model.addAttribute("fsSpecimenout", fsSpecimenout);
		return "modules/specimen/fsSpecimenoutForm";
	}

	@RequiresPermissions("specimen:fsSpecimenout:edit")
	@RequestMapping(value = "save")
	public String save(FsSpecimenout fsSpecimenout, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsSpecimenout)){
			return form(fsSpecimenout, model);
		}
		fsSpecimenoutService.save(fsSpecimenout);
		addMessage(redirectAttributes, "保存样本出库成功");
		return "redirect:"+Global.getAdminPath()+"/specimen/fsSpecimenout/?repage";
	}
	
	@RequiresPermissions("specimen:fsSpecimenout:edit")
	@RequestMapping(value = "delete")
	public String delete(FsSpecimenout fsSpecimenout, RedirectAttributes redirectAttributes) {
		fsSpecimenoutService.delete(fsSpecimenout);
		addMessage(redirectAttributes, "删除样本出库成功");
		return "redirect:"+Global.getAdminPath()+"/specimen/fsSpecimenout/?repage";
	}

}