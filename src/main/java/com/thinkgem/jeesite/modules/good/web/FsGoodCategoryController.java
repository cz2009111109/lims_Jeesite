/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.web;

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
import com.thinkgem.jeesite.modules.good.entity.FsGoodCategory;
import com.thinkgem.jeesite.modules.good.service.FsGoodCategoryService;

/**
 * 商品类别Controller
 * @author chenzhe
 * @version 2019-08-09
 */
@Controller
@RequestMapping(value = "${adminPath}/good/fsGoodCategory")
public class FsGoodCategoryController extends BaseController {

	@Autowired
	private FsGoodCategoryService fsGoodCategoryService;
	
	@ModelAttribute
	public FsGoodCategory get(@RequestParam(required=false) String id) {
		FsGoodCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsGoodCategoryService.get(id);
		}
		if (entity == null){
			entity = new FsGoodCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("good:fsGoodCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsGoodCategory fsGoodCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsGoodCategory> page = fsGoodCategoryService.findPage(new Page<FsGoodCategory>(request, response), fsGoodCategory); 
		model.addAttribute("page", page);
		return "modules/good/fsGoodCategoryList";
	}

	@RequiresPermissions("good:fsGoodCategory:view")
	@RequestMapping(value = "form")
	public String form(FsGoodCategory fsGoodCategory, Model model) {
		model.addAttribute("fsGoodCategory", fsGoodCategory);
		return "modules/good/fsGoodCategoryForm";
	}

	@RequiresPermissions("good:fsGoodCategory:edit")
	@RequestMapping(value = "save")
	public String save(FsGoodCategory fsGoodCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsGoodCategory)){
			return form(fsGoodCategory, model);
		}
		fsGoodCategoryService.save(fsGoodCategory);
		addMessage(redirectAttributes, "保存商品类别管理成功");
		return "redirect:"+Global.getAdminPath()+"/good/fsGoodCategory/?repage";
	}
	
	@RequiresPermissions("good:fsGoodCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(FsGoodCategory fsGoodCategory, RedirectAttributes redirectAttributes) {
		fsGoodCategoryService.delete(fsGoodCategory);
		addMessage(redirectAttributes, "删除商品类别管理成功");
		return "redirect:"+Global.getAdminPath()+"/good/fsGoodCategory/?repage";
	}

}