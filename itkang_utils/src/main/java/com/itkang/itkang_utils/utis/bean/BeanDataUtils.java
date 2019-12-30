package com.itkang.itkang_utils.utis.bean;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
* @Description 在新增修改是将需要重复从后台写入的值抽取为泛型的，传入对应的对象可以将参数补齐
* @author - 林夕
* @Date 2019/12/30 17:17
**/
@Slf4j
public abstract class BeanDataUtils<T> {

    /**
     * 新增时将需要重复写入的基本数据
     * @param t 传入的写入基础数据的实体
     * @param <T>
     */
    public static <T> void getAddBaseData(T t) {
        Field[] beanField = new BeanAddModel().getClass().getDeclaredFields();
        Field[] entityField = t.getClass().getDeclaredFields();
        for (Field entity : entityField) {
            for (Field bean : beanField) {
                if (entity.getName().equals(bean.getName())) {
                    bean.setAccessible(true);
                    entity.setAccessible(true);
                    try {
                        entity.set(t, bean.get(BeanAddModel.class.newInstance()));
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error("反射创建对象失败：" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 修改时将需要重复写入的基本数据
     * @param t 传入的写入基础数据的实体
     * @param <T>
     */
    public static <T> void getUpdateBaseData(T t) {
        Field[] beanField = new BeanUpdateModel().getClass().getDeclaredFields();
        Field[] entityField = t.getClass().getDeclaredFields();
        for (Field entity : entityField) {
            for (Field bean : beanField) {
                if (entity.getName().equals(bean.getName())) {
                    entity.setAccessible(true);
                    try {
                        entity.set(t, bean.get(BeanUpdateModel.class.newInstance()));
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error("反射创建对象失败：" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

