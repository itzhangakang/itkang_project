package com.itkang.itkang_utils.utis.String;

import java.lang.reflect.Field;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/11/27 14:43
 * @explain 通过反射去除接收的实体参数的前后空格
 */
public class FormatStringUtils {

    private <T> void formatStr(T command) throws IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> class1 = command.getClass();
        Field[] declaredFields = class1.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getGenericType().toString().equals("class java.lang.String")) {
                String value = (String) field.get(command);
                if (value != null) {
                    field.set(command, value.trim());
                }
            }
        }
    }
}
