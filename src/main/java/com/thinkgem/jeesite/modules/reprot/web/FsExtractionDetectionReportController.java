/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reprot.web;

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
import com.thinkgem.jeesite.modules.reprot.entity.FsExtractionDetectionReport;
import com.thinkgem.jeesite.modules.reprot.service.FsExtractionDetectionReportService;

/**
 * 提取检测报告Controller
 * @author chenzhe
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/reprot/fsExtractionDetectionReport")
public class FsExtractionDetectionReportController extends BaseController {

	@Autowired
	private FsExtractionDetectionReportService fsExtractionDetectionReportService;
	
	@ModelAttribute
	public FsExtractionDetectionReport get(@RequestParam(required=false) String id) {
		FsExtractionDetectionReport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsExtractionDetectionReportService.get(id);
		}
		if (entity == null){
			entity = new FsExtractionDetectionReport();
		}
		return entity;
	}
	
	@RequiresPermissions("reprot:fsExtractionDetectionReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsExtractionDetectionReport fsExtractionDetectionReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsExtractionDetectionReport> page = fsExtractionDetectionReportService.findPage(new Page<FsExtractionDetectionReport>(request, response), fsExtractionDetectionReport); 
		model.addAttribute("page", page);
		return "modules/reprot/fsExtractionDetectionReportList";
	}

	@RequiresPermissions("reprot:fsExtractionDetectionReport:view")
	@RequestMapping(value = "form")
	public String form(FsExtractionDetectionReport fsExtractionDetectionReport, Model model) {
		model.addAttribute("fsExtractionDetectionReport", fsExtractionDetectionReport);
		return "modules/reprot/fsExtractionDetectionReportForm";
	}

	@RequiresPermissions("reprot:fsExtractionDetectionReport:edit")
	@RequestMapping(value = "save")
	public String save(FsExtractionDetectionReport fsExtractionDetectionReport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsExtractionDetectionReport)){
			return form(fsExtractionDetectionReport, model);
		}
		fsExtractionDetectionReportService.save(fsExtractionDetectionReport);
		addMessage(redirectAttributes, "保存提取检测报告成功");
		return "redirect:"+Global.getAdminPath()+"/reprot/fsExtractionDetectionReport/?repage";
	}
	
	@RequiresPermissions("reprot:fsExtractionDetectionReport:edit")
	@RequestMapping(value = "delete")
	public String delete(FsExtractionDetectionReport fsExtractionDetectionReport, RedirectAttributes redirectAttributes) {
		fsExtractionDetectionReportService.delete(fsExtractionDetectionReport);
		addMessage(redirectAttributes, "删除提取检测报告成功");
		return "redirect:"+Global.getAdminPath()+"/reprot/fsExtractionDetectionReport/?repage";
	}

}