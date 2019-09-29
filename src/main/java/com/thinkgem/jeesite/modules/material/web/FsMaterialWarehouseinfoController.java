/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialWarehouseinfo;
import com.thinkgem.jeesite.modules.material.service.FsMaterialWarehouseinfoService;

/**
 * 仓库库位信息Controller
 * @author chenzhe
 * @version 2019-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/material/fsMaterialWarehouseinfo")
public class FsMaterialWarehouseinfoController extends BaseController {

	@Autowired
	private FsMaterialWarehouseinfoService fsMaterialWarehouseinfoService;
	
	@ModelAttribute
	public FsMaterialWarehouseinfo get(@RequestParam(required=false) String id) {
		FsMaterialWarehouseinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsMaterialWarehouseinfoService.get(id);
		}
		if (entity == null){
			entity = new FsMaterialWarehouseinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("material:fsMaterialWarehouseinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsMaterialWarehouseinfo fsMaterialWarehouseinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<FsMaterialWarehouseinfo> list = fsMaterialWarehouseinfoService.findList(fsMaterialWarehouseinfo); 
		model.addAttribute("list", list);
		return "modules/material/fsMaterialWarehouseinfoList";
	}

	@RequiresPermissions("material:fsMaterialWarehouseinfo:view")
	@RequestMapping(value = "form")
	public String form(FsMaterialWarehouseinfo fsMaterialWarehouseinfo, Model model) {
		if (fsMaterialWarehouseinfo.getParent()!=null && StringUtils.isNotBlank(fsMaterialWarehouseinfo.getParent().getId())){
			fsMaterialWarehouseinfo.setParent(fsMaterialWarehouseinfoService.get(fsMaterialWarehouseinfo.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(fsMaterialWarehouseinfo.getId())){
				FsMaterialWarehouseinfo fsMaterialWarehouseinfoChild = new FsMaterialWarehouseinfo();
				fsMaterialWarehouseinfoChild.setParent(new FsMaterialWarehouseinfo(fsMaterialWarehouseinfo.getParent().getId()));
				List<FsMaterialWarehouseinfo> list = fsMaterialWarehouseinfoService.findList(fsMaterialWarehouseinfo); 
				if (list.size() > 0){
					fsMaterialWarehouseinfo.setSort(list.get(list.size()-1).getSort());
					if (fsMaterialWarehouseinfo.getSort() != null){
						fsMaterialWarehouseinfo.setSort(fsMaterialWarehouseinfo.getSort() + 30);
					}
				}
			}
		}
		if (fsMaterialWarehouseinfo.getSort() == null){
			fsMaterialWarehouseinfo.setSort(30);
		}
		model.addAttribute("fsMaterialWarehouseinfo", fsMaterialWarehouseinfo);
		return "modules/material/fsMaterialWarehouseinfoForm";
	}

	@RequiresPermissions("material:fsMaterialWarehouseinfo:edit")
	@RequestMapping(value = "save")
	public String save(FsMaterialWarehouseinfo fsMaterialWarehouseinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsMaterialWarehouseinfo)){
			return form(fsMaterialWarehouseinfo, model);
		}
		fsMaterialWarehouseinfoService.save(fsMaterialWarehouseinfo);
		addMessage(redirectAttributes, "保存仓库库位信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialWarehouseinfo/?repage";
	}
	
	@RequiresPermissions("material:fsMaterialWarehouseinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(FsMaterialWarehouseinfo fsMaterialWarehouseinfo, RedirectAttributes redirectAttributes) {
		fsMaterialWarehouseinfoService.delete(fsMaterialWarehouseinfo);
		addMessage(redirectAttributes, "删除仓库库位信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialWarehouseinfo/?repage";
	}


	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<FsMaterialWarehouseinfo> list = fsMaterialWarehouseinfoService.findList(new FsMaterialWarehouseinfo());
		for (int i=0; i<list.size(); i++){
			FsMaterialWarehouseinfo e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}