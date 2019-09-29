/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.bank.entity.FsBank;
import com.thinkgem.jeesite.modules.bank.service.FsBankService;
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
import com.thinkgem.jeesite.modules.material.entity.FsMaterialSupplierinfo;
import com.thinkgem.jeesite.modules.material.service.FsMaterialSupplierinfoService;

import java.util.List;

/**
 * 供应商信息Controller
 * @author chenzhe
 * @version 2019-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/material/fsMaterialSupplierinfo")
public class FsMaterialSupplierinfoController extends BaseController {

	@Autowired
	private FsBankService fsBankService;

	@Autowired
	private FsMaterialSupplierinfoService fsMaterialSupplierinfoService;
	
	@ModelAttribute
	public FsMaterialSupplierinfo get(@RequestParam(required=false) String id) {
		FsMaterialSupplierinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fsMaterialSupplierinfoService.get(id);
		}
		if (entity == null){
			entity = new FsMaterialSupplierinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("material:fsMaterialSupplierinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FsMaterialSupplierinfo fsMaterialSupplierinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FsMaterialSupplierinfo> page = fsMaterialSupplierinfoService.findPage(new Page<FsMaterialSupplierinfo>(request, response), fsMaterialSupplierinfo); 
		model.addAttribute("page", page);
		return "modules/material/fsMaterialSupplierinfoList";
	}

	@RequiresPermissions("material:fsMaterialSupplierinfo:view")
	@RequestMapping(value = "form")
	public String form(FsMaterialSupplierinfo fsMaterialSupplierinfo, Model model) {
		FsBank fsBank=new FsBank();
		List<FsBank> fsBanks=fsBankService.findList(fsBank);
		model.addAttribute("fsBanks",fsBanks);
		model.addAttribute("fsMaterialSupplierinfo", fsMaterialSupplierinfo);
		return "modules/material/fsMaterialSupplierinfoForm";
	}

	@RequiresPermissions("material:fsMaterialSupplierinfo:edit")
	@RequestMapping(value = "save")
	public String save(FsMaterialSupplierinfo fsMaterialSupplierinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fsMaterialSupplierinfo)){
			return form(fsMaterialSupplierinfo, model);
		}
		fsMaterialSupplierinfoService.save(fsMaterialSupplierinfo);
		addMessage(redirectAttributes, "保存供应商信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
	}
	
	@RequiresPermissions("material:fsMaterialSupplierinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(FsMaterialSupplierinfo fsMaterialSupplierinfo, RedirectAttributes redirectAttributes) {
		fsMaterialSupplierinfoService.delete(fsMaterialSupplierinfo);
		addMessage(redirectAttributes, "删除供应商信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
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

	@RequiresPermissions("material:fsMaterialSupplierinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(FsMaterialSupplierinfo fsMaterialSupplierinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			Page<FsMaterialSupplierinfo> page = fsMaterialSupplierinfoService.findPage(new Page<FsMaterialSupplierinfo>(request, response, -1), fsMaterialSupplierinfo);
			new ExportExcel("测试FsMaterialSupplierinfo数据", FsMaterialSupplierinfo.class).setDataList(page.getList()).write(response, "测试FsMaterialSupplierinfo数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx").dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
	}


	//导出excel
	@RequiresPermissions("material:fsMaterialSupplierinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<FsMaterialSupplierinfo> list = ei.getDataList(FsMaterialSupplierinfo.class);
			for (FsMaterialSupplierinfo fsMaterialSupplierinfo : list){
				try{
					fsMaterialSupplierinfoService.save(fsMaterialSupplierinfo);
					//System.out.println(fsMaterialSupplierinfo.toString());
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/> "+fsMaterialSupplierinfo.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/> "+fsMaterialSupplierinfo.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
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

	@RequiresPermissions("material:fsMaterialSupplierinfo:edit")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<FsMaterialSupplierinfo> list = Lists.newArrayList();
			list.add(new FsMaterialSupplierinfo());
			new ExportExcel("数据", FsMaterialSupplierinfo.class, 2).setDataList(list).write(response, "数据导入模板.xlsx").dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/material/fsMaterialSupplierinfo/?repage";
	}

}