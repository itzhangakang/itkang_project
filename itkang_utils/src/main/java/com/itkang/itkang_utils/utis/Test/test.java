package com.itkang.itkang_utils.utis.Test;

import com.itkang.itkang_utils.utis.bean.BeanTrimHelper;

import java.util.Date;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/8/23 14:59
 * @explain 去Java 对Bean里面的所有String属性去除前后空格
 */
public class test {

    public static void main(String[] args) {

        BeanDto beanDto = new BeanDto();
        beanDto.setBirthday(new Date());
        beanDto.setName("  aaa aaa aa  ");
        beanDto.setUser("  张三");
        beanDto.setYear(   100  );
        try {
            BeanTrimHelper.beanAttributeValueTrim(beanDto);
            System.out.println(beanDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
