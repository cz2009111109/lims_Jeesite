/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialSupplierinfo;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialWarehouseinfo;
import com.thinkgem.jeesite.modules.material.service.FsMaterialSupplierinfoService;
import com.thinkgem.jeesite.modules.material.service.FsMaterialWarehouseinfoService;
import com.thinkgem.jeesite.modules.sys.utils.MaterialUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.good.entity.FsGood;
import com.thinkgem.jeesite.modules.good.service.FsGoodService;

import java.util.List;

/**
 * 商品列表Controller
 * @author chenzhe
 * @version 2019-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/good/fsGood")
public class FsGoodController extends BaseController {
	
	@Autowired
	private FsMaterialSupplierinfoService fsMaterialSupplierinfoService;
	
	@Autowired
	private FsGoodService fsGoodService;
	
	@ModelAttribute
	public FsGood get(@RequestParam(required=false) String id) {
		FsGood entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsGoodService.get(id);
		}
		if (entity == null){
			entity = new FsGood();
		}
		return entity;
	}
	
	@RequiresPermissions("good:fsGood:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsGood fsGood, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsGood> page = fsGoodService.findPage(new Page<FsGood>(request, response), fsGood);
		model.addAttribute("page", page);
		return "modules/good/fsGoodList";
	}



	@RequiresPermissions("good:fsGood:view")
	@RequestMapping(value = "form")
	public String form(FsGood fsGood, Model model) {
		model.addAttribute("supplierinfos",MaterialUtils.getFsMaterialSupplierinfos());
		model.addAttribute("fsGood", fsGood);
		return "modules/good/fsGoodForm";
	}

	@RequiresPermissions("good:fsGood:edit")
	@RequestMapping(value = "save")
	public String save(FsGood fsGood, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsGood)){
			return form(fsGood, model);
		}
		fsGoodService.save(fsGood);
		addMessage(redirectAttributes, "保存商品列表成功");
		return "redirect:"+Global.getAdminPath()+"/good/fsGood/?repage";
	}
	
	@RequiresPermissions("good:fsGood:edit")
	@RequestMapping(value = "delete")
	public String delete(FsGood fsGood, RedirectAttributes redirectAttributes) {
		fsGoodService.delete(fsGood);
		addMessage(redirectAttributes, "删除商品列表成功");
		return "redirect:"+Global.getAdminPath()+"/good/fsGood/?repage";
	}

	//导入excel
	/**
	 * @author chenzhe
	 * @creed: Talk is cheap,show me the code
	 * @date 2019/8/7 15:58
	 * 导出数据
	 * [user, request, response, redirectAttributes] 
	 * @return java.lang.String
	 */

	@RequiresPermissions("good:fsGood:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(FsGood fsGood, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<FsGood> page = fsGoodService.findPage(new Page<FsGood>(request, response, -1), fsGood);
			new ExportExcel("数据", FsGood.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/good/fsGoodForm/?repage";
	}


	//导出excel
	@RequiresPermissions("good:fsGood:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/good/fsGoodForm/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<FsGood> list = ei.getDataList(FsGood.class);
			for (FsGood fsGood : list){
				try{
					fsGoodService.save(fsGood);
					//System.out.println(fsGood.toString());
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/> "+fsGood.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/> "+fsGood.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/test/fsGood/?repage";
	}

//导出excel模板
	/**
	 * @author chenzhe
	 * @creed: Talk is cheap,show me the code
	 * @date 2019/8/7 16:17
	 * 导出模板
	 * [response, redirectAttributes] 
	 * @return java.lang.String
	 */

	@RequiresPermissions("good:fsGood:edit")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "数据导入模板.xlsx";
			List<FsGood> list = Lists.newArrayList();
			list.add(new FsGood());
			new ExportExcel("数据", FsGood.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/good/fsGoodForm/?repage";
	}

	@RequiresPermissions("good:fsGood:view")
	@RequestMapping(value = {"fsGoodlist"})
	public String fsGoodlist(FsGood fsGood, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsGood> page = fsGoodService.findPage(new Page<FsGood>(request, response), fsGood);
		model.addAttribute("page", page);
		return "modules/material/fsMaterialGoodList";
	}

}