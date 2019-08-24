/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bank.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.bank.entity.FsBank;
import com.thinkgem.jeesite.modules.bank.service.FsBankService;

import java.util.List;

/**
 * 开票银行账号Controller
 * @author chenzhe
 * @version 2019-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/bank/fsBank")
public class FsBankController extends BaseController {


	@Autowired
	private FsBankService fsBankService;
	
	@ModelAttribute
	public FsBank get(@RequestParam(required=false) String id) {
		FsBank entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsBankService.get(id);
		}
		if (entity == null){
			entity = new FsBank();
		}
		return entity;
	}
	
	@RequiresPermissions("bank:fsBank:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsBank fsBank, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsBank> page = fsBankService.findPage(new Page<FsBank>(request, response), fsBank);
		model.addAttribute("page", page);
		return "modules/bank/fsBankList";
	}



	@RequestMapping(value = "selectlist")
	@ResponseBody
	public List<FsBank> selectlist(FsBank fsBank, HttpServletRequest request, HttpServletResponse response) {

		List<FsBank> list = fsBankService.findList(fsBank);
		return list;
	}

	@RequiresPermissions("bank:fsBank:view")
	@RequestMapping(value = "form")
	public String form(FsBank fsBank, Model model) {
		model.addAttribute("fsBank", fsBank);
		return "modules/bank/fsBankForm";
	}

	@RequiresPermissions("bank:fsBank:edit")
	@RequestMapping(value = "save")
	public String save(FsBank fsBank, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsBank)){
			return form(fsBank, model);
		}
		fsBankService.save(fsBank);
		addMessage(redirectAttributes, "保存开票银行账号成功");
		return "redirect:"+Global.getAdminPath()+"/bank/fsBank/?repage";
	}
	
	@RequiresPermissions("bank:fsBank:edit")
	@RequestMapping(value = "delete")
	public String delete(FsBank fsBank, RedirectAttributes redirectAttributes) {
		fsBankService.delete(fsBank);
		addMessage(redirectAttributes, "删除开票银行账号成功");
		return "redirect:"+Global.getAdminPath()+"/bank/fsBank/?repage";
	}

}