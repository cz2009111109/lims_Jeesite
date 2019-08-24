/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.project.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.project.entity.FsProject;
import com.thinkgem.jeesite.modules.project.service.FsProjectService;

import java.util.List;
import java.util.Map;

/**
 * 项目管理Controller
 * @author chenzhe
 * @version 2019-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/project/fsProject")
public class FsProjectController extends BaseController {

	@Autowired
	private FsProjectService fsProjectService;
	
	@ModelAttribute
	public FsProject get(@RequestParam(required=false) String id) {
		FsProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsProjectService.get(id);
		}
		if (entity == null){
			entity = new FsProject();
		}
		return entity;
	}
	
	@RequiresPermissions("project:fsProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsProject fsProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsProject> page = fsProjectService.findPage(new Page<FsProject>(request, response), fsProject); 
		model.addAttribute("page", page);
		return "modules/project/fsProjectList";
	}

	@RequiresPermissions("project:fsProject:view")
	@RequestMapping(value = "form")
	public String form(FsProject fsProject, Model model) {
		model.addAttribute("fsProject", fsProject);
		return "modules/project/fsProjectForm";
	}

	@RequiresPermissions("project:fsProject:edit")
	@RequestMapping(value = "save")
	public String save(FsProject fsProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsProject)){
			return form(fsProject, model);
		}
		fsProjectService.save(fsProject);
		addMessage(redirectAttributes, "保存项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/project/fsProject/?repage";
	}
	
	@RequiresPermissions("project:fsProject:edit")
	@RequestMapping(value = "delete")
	public String delete(FsProject fsProject, RedirectAttributes redirectAttributes) {
		fsProjectService.delete(fsProject);
		addMessage(redirectAttributes, "删除项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/project/fsProject/?repage";
	}


	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<FsProject> list = fsProjectService.findList(new FsProject());
		for (int i=0; i<list.size(); i++){
			FsProject e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				mapList.add(map);
			}
		}
		return mapList;
	}

	@ResponseBody
	@RequestMapping(value = "listData")
	public Page<FsProject> listData( FsProject fsProject, HttpServletRequest request, HttpServletResponse response) {
		Page<FsProject> page = fsProjectService.findPage(new Page<FsProject>(request, response), fsProject);
		return page;
	}


}