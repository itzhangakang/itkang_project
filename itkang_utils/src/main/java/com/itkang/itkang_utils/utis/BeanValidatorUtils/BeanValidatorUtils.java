package com.itkang.itkang_utils.utis.BeanValidatorUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/12/30 17:21
 * @explain 数据在写入数据库前再java中需要对必填字段做校验
 * 1.在save前调用，出入插入insert的实体类；
 * 2.实体类中对应的必填字段需要加@NotNull(message = 'xxx不能为空')
 */
public class BeanValidatorUtils {
    public static boolean validateInput(Object t) throws Exception {
        if (t == null) {
            throw new Exception("校验实体为空");
        }
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();// 获取校验器
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(t);// 校验参数获得校验信息
        Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();
        if (iterator.hasNext()) {
            String message = "错误信息:";
            while (iterator.hasNext()) {
                ConstraintViolation<Object> next = iterator.next();
                message = message.concat(next.getMessage());
                if (iterator.hasNext()) {
                    message = message.concat(",");
                }
            }
            throw new Exception("校验错误");
        } else {
            return true;
        }
    }
}
