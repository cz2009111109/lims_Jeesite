package com.thinkgem.jeesite.modules.material.entity;

import java.util.List;

/**
 * @ProjectName: jeesite
 * @Package: com.thinkgem.jeesite.modules.material.entity
 * @ClassName: FsMaterialPurchasingorderlistParamVo
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/9/8 20:31
 * @Version: 1.0
 */
public class FsMaterialPurchasingorderlistParamVo {
    private List<FsMaterialPurchasingorderlistParam> list;

    public List<FsMaterialPurchasingorderlistParam> getList() {
        return list;
    }

    public void setList(List<FsMaterialPurchasingorderlistParam> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FsMaterialPurchasingorderlistParamVo{" +
                "list=" + list +
                '}';
    }

}
