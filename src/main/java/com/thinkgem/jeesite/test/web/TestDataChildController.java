/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
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
import com.thinkgem.jeesite.test.entity.TestDataChild;
import com.thinkgem.jeesite.test.service.TestDataChildService;

import java.util.List;

/**
 * 主子表子表Controller
 * @author chenzhe
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testDataChild")
public class TestDataChildController extends BaseController {

	@Autowired
	private TestDataChildService testDataChildService;
	
	@ModelAttribute
	public TestDataChild get(@RequestParam(required=false) String id) {
		TestDataChild entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataChildService.get(id);
		}
		if (entity == null){
			entity = new TestDataChild();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testDataChild:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestDataChild testDataChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestDataChild> page = testDataChildService.findPage(new Page<TestDataChild>(request, response), testDataChild); 
		model.addAttribute("page", page);
		return "jeesite/test/testDataChildList";
	}

	@RequiresPermissions("test:testDataChild:view")
	@RequestMapping(value = "form")
	public String form(TestDataChild testDataChild, Model model) {
		model.addAttribute("testDataChild", testDataChild);
		return "jeesite/test/testDataChildForm";
	}

	@RequiresPermissions("test:testDataChild:edit")
	@RequestMapping(value = "save")
	public String save(TestDataChild testDataChild, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testDataChild)){
			return form(testDataChild, model);
		}
		testDataChildService.save(testDataChild);
		addMessage(redirectAttributes, "保存主子表子表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testDataChild/?repage";
	}
	
	@RequiresPermissions("test:testDataChild:edit")
	@RequestMapping(value = "delete")
	public String delete(TestDataChild testDataChild, RedirectAttributes redirectAttributes) {
		testDataChildService.delete(testDataChild);
		addMessage(redirectAttributes, "删除主子表子表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testDataChild/?repage";
	}


}