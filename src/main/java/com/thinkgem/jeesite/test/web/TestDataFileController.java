/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.web;

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
import com.thinkgem.jeesite.test.entity.TestDataFile;
import com.thinkgem.jeesite.test.service.TestDataFileService;

/**
 * 测试附件Controller
 * @author chenzhe
 * @version 2019-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testDataFile")
public class TestDataFileController extends BaseController {

	@Autowired
	private TestDataFileService testDataFileService;
	
	@ModelAttribute
	public TestDataFile get(@RequestParam(required=false) String id) {
		TestDataFile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataFileService.get(id);
		}
		if (entity == null){
			entity = new TestDataFile();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testDataFile:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestDataFile testDataFile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestDataFile> page = testDataFileService.findPage(new Page<TestDataFile>(request, response), testDataFile); 
		model.addAttribute("page", page);
		return "jeesite/test/testDataFileList";
	}

	@RequiresPermissions("test:testDataFile:view")
	@RequestMapping(value = "form")
	public String form(TestDataFile testDataFile, Model model) {
		model.addAttribute("testDataFile", testDataFile);
		return "jeesite/test/testDataFileForm";
	}

	@RequiresPermissions("test:testDataFile:edit")
	@RequestMapping(value = "save")
	public String save(TestDataFile testDataFile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testDataFile)){
			return form(testDataFile, model);
		}
		testDataFileService.save(testDataFile);
		addMessage(redirectAttributes, "保存测试附件成功");
		return "redirect:"+Global.getAdminPath()+"/test/testDataFile/?repage";
	}
	
	@RequiresPermissions("test:testDataFile:edit")
	@RequestMapping(value = "delete")
	public String delete(TestDataFile testDataFile, RedirectAttributes redirectAttributes) {
		testDataFileService.delete(testDataFile);
		addMessage(redirectAttributes, "删除测试附件成功");
		return "redirect:"+Global.getAdminPath()+"/test/testDataFile/?repage";
	}

}