package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.bank.dao.FsBankDao;
import com.thinkgem.jeesite.modules.bank.entity.FsBank;
import com.thinkgem.jeesite.modules.material.dao.FsMaterialSupplierinfoDao;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialSupplierinfo;
import com.thinkgem.jeesite.modules.material.service.FsMaterialSupplierinfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * @ProjectName: jeesite
 * @Package: com.thinkgem.jeesite.modules.sys.utils
 * @ClassName: MaterialUtils
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/8/27 10:49
 * @Version: 1.0
 */
public class MaterialUtils {
    private static FsBankDao fsBankDao = SpringContextHolder.getBean(FsBankDao.class);
    private static FsMaterialSupplierinfoDao fsMaterialSupplierinfoService = SpringContextHolder.getBean(FsMaterialSupplierinfoDao.class);

    public static final String CACHE_MATERIAL_FS_BANK = "Material_fsBank";
    public static final String CACHE_MATERIAL_SUPPLIERINFO_NAME="Material_SN";

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
//			subject.logout();
        }catch (InvalidSessionException e){

        }
        return null;
    }

    public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
        Object obj = getSession().getAttribute(key);
        return obj==null?defaultValue:obj;
    }

    public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
//		getCacheMap().remove(key);
        getSession().removeAttribute(key);
    }

    public static List<FsBank> getFsBanks(){
        List<FsBank> fsBankList = (List<FsBank>)getCache(CACHE_MATERIAL_FS_BANK);
        if (fsBankList == null){
            FsBank fsBank = new FsBank();
            fsBankList = fsBankDao.findList(fsBank);
            putCache(CACHE_MATERIAL_FS_BANK, fsBankList);
        }
        return fsBankList;
    }

    public static List<FsMaterialSupplierinfo> getFsMaterialSupplierinfos(){
        List<FsMaterialSupplierinfo> fsMaterialSupplierinfoList = (List<FsMaterialSupplierinfo>)getCache(CACHE_MATERIAL_SUPPLIERINFO_NAME);


        if(fsMaterialSupplierinfoList==null){
            FsMaterialSupplierinfo fsMaterialSupplierinfo= new FsMaterialSupplierinfo();
            fsMaterialSupplierinfoList = fsMaterialSupplierinfoService.findAllList(fsMaterialSupplierinfo);
            putCache(CACHE_MATERIAL_FS_BANK, fsMaterialSupplierinfoList);
        }
        return fsMaterialSupplierinfoList;
    }
}
