package com.thinkgem.jeesite.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: jeesite
 * @Package: com.thinkgem.jeesite.common.utils
 * @ClassName: ArrayUtils
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/9/3 10:16
 * @Version: 1.0
 */
public class ArrayUtils {

    /*
     * @author chenzhe
     * @creed: Talk is cheap,show me the code
     * @date 2019/9/3 10:17
     *  数组转为List
     *
     * @return List<String>
     */

    public static List<String> ArrayToList(String[] strings){
        List<String> tmp = new ArrayList<String>();
        for(String str:strings){
            if(str!=null && str.length()!=0){
                tmp.add(str);
            }
        }
        return tmp;
    }
}
