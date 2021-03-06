package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bank.entity.FsBank;
import com.thinkgem.jeesite.modules.bank.service.FsBankService;
import com.thinkgem.jeesite.modules.sys.utils.MaterialUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName: jeesite
 * @Package: com.thinkgem.jeesite.common.utils.excel.fieldtype
 * @ClassName: FsBankType
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/8/27 10:39
 * @Version: 1.0
 */
public class FsBankType {

    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {

        for (FsBank e : MaterialUtils.getFsBanks()){
            if (StringUtils.trimToEmpty(val).equals(e.getOpenBank())){
                return e;
            }
        }
        return null;
    }

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null && ((FsBank)val).getOpenBank() != null){
            return ((FsBank)val).getOpenBank();
        }
        return "";
    }


}
