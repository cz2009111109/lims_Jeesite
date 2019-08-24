/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.web;

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
import com.thinkgem.jeesite.modules.task.entity.FsExtractionDetectionTask;
import com.thinkgem.jeesite.modules.task.service.FsExtractionDetectionTaskService;

/**
 * 提取检测任务Controller
 * @author chenzhe
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/task/fsExtractionDetectionTask")
public class FsExtractionDetectionTaskController extends BaseController {

	@Autowired
	private FsExtractionDetectionTaskService fsExtractionDetectionTaskService;
	
	@ModelAttribute
	public FsExtractionDetectionTask get(@RequestParam(required=false) String id) {
		FsExtractionDetectionTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsExtractionDetectionTaskService.get(id);
		}
		if (entity == null){
			entity = new FsExtractionDetectionTask();
		}
		return entity;
	}
	
	@RequiresPermissions("task:fsExtractionDetectionTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsExtractionDetectionTask fsExtractionDetectionTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsExtractionDetectionTask> page = fsExtractionDetectionTaskService.findPage(new Page<FsExtractionDetectionTask>(request, response), fsExtractionDetectionTask); 
		model.addAttribute("page", page);
		return "modules/task/fsExtractionDetectionTaskList";
	}

	@RequiresPermissions("task:fsExtractionDetectionTask:view")
	@RequestMapping(value = "form")
	public String form(FsExtractionDetectionTask fsExtractionDetectionTask, Model model) {
		model.addAttribute("fsExtractionDetectionTask", fsExtractionDetectionTask);
		return "modules/task/fsExtractionDetectionTaskForm";
	}

	@RequiresPermissions("task:fsExtractionDetectionTask:edit")
	@RequestMapping(value = "save")
	public String save(FsExtractionDetectionTask fsExtractionDetectionTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsExtractionDetectionTask)){
			return form(fsExtractionDetectionTask, model);
		}
		fsExtractionDetectionTaskService.save(fsExtractionDetectionTask);
		addMessage(redirectAttributes, "保存提取检测任务成功");
		return "redirect:"+Global.getAdminPath()+"/task/fsExtractionDetectionTask/?repage";
	}
	
	@RequiresPermissions("task:fsExtractionDetectionTask:edit")
	@RequestMapping(value = "delete")
	public String delete(FsExtractionDetectionTask fsExtractionDetectionTask, RedirectAttributes redirectAttributes) {
		fsExtractionDetectionTaskService.delete(fsExtractionDetectionTask);
		addMessage(redirectAttributes, "删除提取检测任务成功");
		return "redirect:"+Global.getAdminPath()+"/task/fsExtractionDetectionTask/?repage";
	}

}