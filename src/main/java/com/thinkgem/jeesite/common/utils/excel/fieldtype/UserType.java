package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * @ProjectName: jeesite
 * @Package: com.thinkgem.jeesite.common.utils.excel.fieldtype
 * @ClassName: UserType
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/8/23 8:58
 * @Version: 1.0
 */
public class UserType {
    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {
        for (User e : UserUtils.getUserList()){
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
        if (val != null && ((User)val).getName() != null){
            return ((User)val).getName();
        }
        return "";
    }
}
