package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialSupplierinfo;
import com.thinkgem.jeesite.modules.sys.utils.MaterialUtils;

/**
 * @ProjectName: jeesite
 * @Package: com.thinkgem.jeesite.common.utils.excel.fieldtype
 * @ClassName: FsMaterialSupplierinfoType
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/8/27 15:01
 * @Version: 1.0
 */
public class FsMaterialSupplierinfoType {

    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {

        for (FsMaterialSupplierinfo e : MaterialUtils.getFsMaterialSupplierinfos()){
            if (StringUtils.trimToEmpty(val).equals(e.getName())){
                return e;
            }
        }
        return null;
    }

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null && ((FsMaterialSupplierinfo)val).getName() != null){
            return ((FsMaterialSupplierinfo)val).getName();
        }
        return "";
    }

}
