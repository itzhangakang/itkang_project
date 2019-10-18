package com.itkang.itkang_utils.utis.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/9/12 9:46
 * @explain 集合取差集测试
 */
public class test2 {
    public static void main(String[] args) {

        String mwrapIdOpinion = "'1','2','3','4','5','6','7','8','9','10','q','1','2','3'";
        List<String> list = getList(mwrapIdOpinion);

        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String arg = i + "";
            list2.add(arg);
        }
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("7");
        list2.add("7");
        list2.add("99");
        for (String s : list2) {
          //  System.err.println(s + "===");
        }
        List<String> differenceList = getDifferenceList(list, list2);
        for (String s : differenceList) {
            System.err.println(s);
        }
    }
    /**
     * 比较传入的in语法的字符串和从数据库中查询返回集合中的差集
     *
     * @param string 例：String mwrapIdOpinion = "'1','2','3','4','5','6','7','8','9','10'";
     * @param
     * @return
     */
    public static List<String> getList(String string){
        // 去掉字符串的首位单引号
        String m1 = string.substring(1, string.length() - 1);
        List<String> list = new ArrayList<>();
        String[] split = m1.split("','");
        for (String s : split) {
            list.add(s);
        }
        return list;
    }
    public static List<String> getDifferenceList(List<String> list, List<String> list2) {
        List<String> list3 = new ArrayList<>();
        for (String s : list) {
            if (!list2.contains(s)) {
                list3.add(s);
            }
        }
       /* for (String s : list2) {
            if (!list.contains(s)) {
                list3.add(s);
            }
        }*/
        return list3;
    }
}
