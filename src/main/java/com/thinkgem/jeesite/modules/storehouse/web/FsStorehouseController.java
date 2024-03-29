/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.storehouse.web;

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
import com.thinkgem.jeesite.modules.storehouse.entity.FsStorehouse;
import com.thinkgem.jeesite.modules.storehouse.service.FsStorehouseService;

/**
 * 仓库位置Controller
 * @author chenzhe
 * @version 2019-08-12
 */
@Controller
@RequestMapping(value = "${adminPath}/storehouse/fsStorehouse")
public class FsStorehouseController extends BaseController {

	@Autowired
	private FsStorehouseService fsStorehouseService;
	
	@ModelAttribute
	public FsStorehouse get(@RequestParam(required=false) String id) {
		FsStorehouse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsStorehouseService.get(id);
		}
		if (entity == null){
			entity = new FsStorehouse();
		}
		return entity;
	}
	
	@RequiresPermissions("storehouse:fsStorehouse:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsStorehouse fsStorehouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<FsStorehouse> list = fsStorehouseService.findList(fsStorehouse); 
		model.addAttribute("list", list);
		return "modules/storehouse/fsStorehouseList";
	}

	@RequiresPermissions("storehouse:fsStorehouse:view")
	@RequestMapping(value = "form")
	public String form(FsStorehouse fsStorehouse, Model model) {
		if (fsStorehouse.getParent()!=null && StringUtils.isNotBlank(fsStorehouse.getParent().getId())){
			fsStorehouse.setParent(fsStorehouseService.get(fsStorehouse.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(fsStorehouse.getId())){
				FsStorehouse fsStorehouseChild = new FsStorehouse();
				fsStorehouseChild.setParent(new FsStorehouse(fsStorehouse.getParent().getId()));
				List<FsStorehouse> list = fsStorehouseService.findList(fsStorehouse); 
				if (list.size() > 0){
					fsStorehouse.setSort(list.get(list.size()-1).getSort());
					if (fsStorehouse.getSort() != null){
						fsStorehouse.setSort(fsStorehouse.getSort() + 30);
					}
				}
			}
		}
		if (fsStorehouse.getSort() == null){
			fsStorehouse.setSort(30);
		}
		model.addAttribute("fsStorehouse", fsStorehouse);
		return "modules/storehouse/fsStorehouseForm";
	}

	@RequiresPermissions("storehouse:fsStorehouse:edit")
	@RequestMapping(value = "save")
	public String save(FsStorehouse fsStorehouse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsStorehouse)){
			return form(fsStorehouse, model);
		}
		fsStorehouseService.save(fsStorehouse);
		addMessage(redirectAttributes, "保存仓库位置成功");
		return "redirect:"+Global.getAdminPath()+"/storehouse/fsStorehouse/?repage";
	}
	
	@RequiresPermissions("storehouse:fsStorehouse:edit")
	@RequestMapping(value = "delete")
	public String delete(FsStorehouse fsStorehouse, RedirectAttributes redirectAttributes) {
		fsStorehouseService.delete(fsStorehouse);
		addMessage(redirectAttributes, "删除仓库位置成功");
		return "redirect:"+Global.getAdminPath()+"/storehouse/fsStorehouse/?repage";
	}



	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<FsStorehouse> list = fsStorehouseService.findList(new FsStorehouse());
		for (int i=0; i<list.size(); i++){
			FsStorehouse e = list.get(i);
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