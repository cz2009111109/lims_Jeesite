/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.project.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.project.entity.FsProject;
import com.thinkgem.jeesite.modules.project.service.FsProjectService;
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
import com.thinkgem.jeesite.modules.project.entity.FsSubproject;
import com.thinkgem.jeesite.modules.project.service.FsSubprojectService;

import java.util.List;

/**
 * 子项目管理Controller
 * @author chenzhe
 * @version 2019-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/project/fsSubproject")
public class FsSubprojectController extends BaseController {

	@Autowired
	private FsProjectService fsProjectService;

	@Autowired
	private FsSubprojectService fsSubprojectService;
	
	@ModelAttribute
	public FsSubproject get(@RequestParam(required=false) String id) {
		FsSubproject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsSubprojectService.get(id);
		}
		if (entity == null){
			entity = new FsSubproject();
		}
		return entity;
	}
	
	@RequiresPermissions("project:fsSubproject:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsSubproject fsSubproject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsSubproject> page = fsSubprojectService.findPage(new Page<FsSubproject>(request, response), fsSubproject); 
		model.addAttribute("page", page);
		return "modules/project/fsSubprojectList";
	}

	@RequiresPermissions("project:fsSubproject:view")
	@RequestMapping(value = "form")
	public String form(FsSubproject fsSubproject, Model model) {
		FsProject fsProject=new FsProject();
		List<FsProject> fsProjects = fsProjectService.findList(fsProject);
		model.addAttribute("fsProjects", fsProjects);
		model.addAttribute("fsSubproject", fsSubproject);
		return "modules/project/fsSubprojectForm";
	}

	@RequiresPermissions("project:fsSubproject:edit")
	@RequestMapping(value = "save")
	public String save(FsSubproject fsSubproject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsSubproject)){
			return form(fsSubproject, model);
		}
		fsSubprojectService.save(fsSubproject);
		addMessage(redirectAttributes, "保存子项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/project/fsSubproject/?repage";
	}
	
	@RequiresPermissions("project:fsSubproject:edit")
	@RequestMapping(value = "delete")
	public String delete(FsSubproject fsSubproject, RedirectAttributes redirectAttributes) {
		fsSubprojectService.delete(fsSubproject);
		addMessage(redirectAttributes, "删除子项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/project/fsSubproject/?repage";
	}

}