/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.cooperation.entity.FsGoodCooperation;
import com.thinkgem.jeesite.modules.cooperation.service.FsGoodCooperationService;
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
import com.thinkgem.jeesite.modules.good.entity.FsGood;
import com.thinkgem.jeesite.modules.good.service.FsGoodService;

import java.util.List;

/**
 * 商品列表Controller
 * @author chenzhe
 * @version 2019-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/good/fsGood")
public class FsGoodController extends BaseController {
	
	@Autowired
	private FsGoodCooperationService fsGoodCooperationService;
	
	@Autowired
	private FsGoodService fsGoodService;
	
	@ModelAttribute
	public FsGood get(@RequestParam(required=false) String id) {
		FsGood entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsGoodService.get(id);
		}
		if (entity == null){
			entity = new FsGood();
		}
		return entity;
	}
	
	@RequiresPermissions("good:fsGood:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsGood fsGood, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsGood> page = fsGoodService.findPage(new Page<FsGood>(request, response), fsGood); 
		model.addAttribute("page", page);
		return "modules/good/fsGoodList";
	}

	@RequiresPermissions("good:fsGood:view")
	@RequestMapping(value = "form")
	public String form(FsGood fsGood, Model model) {
		FsGoodCooperation fsGoodCooperation=new FsGoodCooperation();
		List<FsGoodCooperation> fsGoodCooperations = fsGoodCooperationService.findList(fsGoodCooperation);
		model.addAttribute("fsGoodCooperations",fsGoodCooperations);
		model.addAttribute("fsGood", fsGood);
		return "modules/good/fsGoodForm";
	}

	@RequiresPermissions("good:fsGood:edit")
	@RequestMapping(value = "save")
	public String save(FsGood fsGood, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsGood)){
			return form(fsGood, model);
		}
		fsGoodService.save(fsGood);
		addMessage(redirectAttributes, "保存商品列表成功");
		return "redirect:"+Global.getAdminPath()+"/good/fsGood/?repage";
	}
	
	@RequiresPermissions("good:fsGood:edit")
	@RequestMapping(value = "delete")
	public String delete(FsGood fsGood, RedirectAttributes redirectAttributes) {
		fsGoodService.delete(fsGood);
		addMessage(redirectAttributes, "删除商品列表成功");
		return "redirect:"+Global.getAdminPath()+"/good/fsGood/?repage";
	}

}