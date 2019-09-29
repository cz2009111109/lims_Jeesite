/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.JsonData;
import com.thinkgem.jeesite.modules.material.entity.*;
import com.thinkgem.jeesite.modules.material.service.FsMaterialPurchasingorderService;
import com.thinkgem.jeesite.modules.material.service.FsMaterialPurchasingorderlistInService;
import com.thinkgem.jeesite.modules.material.service.FsMaterialPurchasingorderlistStockService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.material.service.FsMaterialPurchasingorderlistService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;

/**
 * 申购单货物Controller
 * @author chenzhe
 * @version 2019-08-28
 */
@Controller
@RequestMapping(value = "${adminPath}/material/fsMaterialPurchasingorderlist")
public class FsMaterialPurchasingorderlistController extends BaseController {
	private static String EMPTYSTRING="";

	@Autowired
	private FsMaterialPurchasingorderlistService fsMaterialPurchasingorderlistService;

	@Autowired
	private FsMaterialPurchasingorderService fsMaterialPurchasingorderService;


	@ModelAttribute
	public FsMaterialPurchasingorderlist get(@RequestParam(required=false) String id) {
		FsMaterialPurchasingorderlist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsMaterialPurchasingorderlistService.get(id);
		}
		if (entity == null){
			entity = new FsMaterialPurchasingorderlist();
		}
		return entity;
	}
	
	@RequiresPermissions("material:fsMaterialPurchasingorderlist:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsMaterialPurchasingorderlist> page = fsMaterialPurchasingorderlistService.findPage(new Page<FsMaterialPurchasingorderlist>(request, response), fsMaterialPurchasingorderlist); 
		model.addAttribute("page", page);
		return "modules/material/fsMaterialPurchasingorderlistList";
	}

	@RequiresPermissions("material:fsMaterialPurchasingorderlist:view")
	@RequestMapping(value = {"pay"})
	public String list1(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsMaterialPurchasingorderlist> page = fsMaterialPurchasingorderlistService.findPage(new Page<FsMaterialPurchasingorderlist>(request, response), fsMaterialPurchasingorderlist);
		model.addAttribute("page", page);
		return "modules/material/fsMaterialPurchasingorderlistListPayment";
	}

	@RequiresPermissions("material:fsMaterialPurchasingorderlist:view")
	@RequestMapping(value = {"invoice"})
	public String list2(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsMaterialPurchasingorderlist> page = fsMaterialPurchasingorderlistService.findPage(new Page<FsMaterialPurchasingorderlist>(request, response), fsMaterialPurchasingorderlist);
		model.addAttribute("page", page);
		return "modules/material/fsMaterialPurchasingorderlistListInvoice";
	}

	@RequiresPermissions("material:fsMaterialPurchasingorderlist:view")
	@RequestMapping(value = "form")
	public String form(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist, Model model) {
		model.addAttribute("fsMaterialPurchasingorderlist", fsMaterialPurchasingorderlist);
		return "modules/material/fsMaterialPurchasingorderlistForm";
	}

	@RequiresPermissions("material:fsMaterialPurchasingorderlist:edit")
	@RequestMapping(value = "save")
	public String save(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsMaterialPurchasingorderlist)){
			return form(fsMaterialPurchasingorderlist, model);
		}
		if(fsMaterialPurchasingorderlist.getOrderId().getId()!=null
				&& !fsMaterialPurchasingorderlist.getOrderId().getId().equals(EMPTYSTRING)
				&& fsMaterialPurchasingorderService.get(fsMaterialPurchasingorderlist.getOrderId().getId())!=null
				&& fsMaterialPurchasingorderService.get(fsMaterialPurchasingorderlist.getOrderId().getId()).getStatus()==1){
			fsMaterialPurchasingorderlistService.save(fsMaterialPurchasingorderlist);
			addMessage(redirectAttributes, "保存申购单货物成功");
		}else{
			addMessage(redirectAttributes, "无法修改申购单货物");
		}


		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialPurchasingorderlist/?repage";
	}
	
	@RequiresPermissions("material:fsMaterialPurchasingorderlist:edit")
	@RequestMapping(value = "delete")
	public String delete(FsMaterialPurchasingorderlist fsMaterialPurchasingorderlist, RedirectAttributes redirectAttributes) {
		fsMaterialPurchasingorderlistService.delete(fsMaterialPurchasingorderlist);
		addMessage(redirectAttributes, "删除申购单货物成功");
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialPurchasingorderlist/?repage";
	}

	@RequiresPermissions("material:fsMaterialPurchasingorderlist:edit")
	@RequestMapping(value = "objectIn")
	@ResponseBody
	public JsonData objectIn(@RequestBody FsMaterialPurchasingorderlistParam[] Params,
							 HttpServletRequest request,
							 HttpServletResponse response){
		StringBuilder Msg = new StringBuilder();
		for(FsMaterialPurchasingorderlistParam object: Params){
			Msg.append(fsMaterialPurchasingorderlistService.objectIn(object)+"<br/>");
		}
		return JsonData.success(Msg.toString());
	}

	@RequiresPermissions("material:fsMaterialPurchasingorderlist:edit")
	@RequestMapping(value = "paymentsubmit")
	@ResponseBody
	public JsonData payment(@RequestBody FsMaterialPurchasingorderlistParam[] Params,
							 HttpServletRequest request,
							 HttpServletResponse response){
		StringBuilder Msg = new StringBuilder();
		for(FsMaterialPurchasingorderlistParam object: Params){
			Msg.append(fsMaterialPurchasingorderlistService.payment(object)+"<br/>");
		}
		return JsonData.success(Msg.toString());
	}

	@RequiresPermissions("material:fsMaterialPurchasingorderlist:edit")
	@RequestMapping(value = "invoicesubmit")
	@ResponseBody
	public JsonData invoice(@RequestBody FsMaterialPurchasingorderlistParam[] Params,
							 HttpServletRequest request,
							 HttpServletResponse response){
		StringBuilder Msg = new StringBuilder();
		for(FsMaterialPurchasingorderlistParam object: Params){
			Msg.append(fsMaterialPurchasingorderlistService.invoice(object)+"<br/>");
		}
		return JsonData.success(Msg.toString());
	}


	@RequestMapping(value = "test")
	@ResponseBody
	public JsonData Test(@RequestBody FsMaterialPurchasingorderlistParamVo list, HttpServletRequest request,
						 HttpServletResponse response){
		String url = request.getRequestURI().toString();
		Map parameterMap = request.getParameterMap();
		if(list==null){
			return JsonData.success("uesr:"+UserUtils.getUser().toString()+";url:"+url+";param:空的");
		}else{
			return JsonData.success("uesr:"+UserUtils.getUser().toString()+";url:"+url+";param:"+ list.getList().size());
		}

	}

	@RequestMapping(value = "test1")
	@ResponseBody
	public JsonData Test1(@RequestBody FsMaterialPurchasingorderlistParam[] list, HttpServletRequest request,
						 HttpServletResponse response){
		String url = request.getRequestURI().toString();
		Map parameterMap = request.getParameterMap();
		if(list==null){
			return JsonData.success("uesr:"+UserUtils.getUser().toString()+";url:"+url+";param:空的");
		}else{
			return JsonData.success("uesr:"+UserUtils.getUser().toString()+";url:"+url+";param:"+ list.length);
		}

	}


}