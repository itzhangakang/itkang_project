package com.itkang.itkang_utils.utis.ThreadLocalUtils;

import com.itkang.itkang_utils.utis.passwordUtils.EncryptUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - 林夕
 * @Description ThreadLocal 适用于如下两种场景
 * <p>
 * 每个线程需要有自己单独的实例
 * 实例需要在多个方法中共享，但不希望被多线程共享
 * 对于第一点，每个线程拥有自己实例，实现它的方式很多。例如可以在线程内部构建一个单独的实例。ThreadLoca 可以以非常方便的形式满足该需求。
 * <p>
 * 对于第二点，可以在满足第一点（每个线程有自己的实例）的条件下，通过方法间引用传递的形式实现。ThreadLocal 使得代码耦合度更低，且实现更优雅。
 * <p>
 * 1）存储用户Session
 * 2）解决线程安全的问题
 * @Date 2020/6/23 14:18
 **/
public class UserInfoLocalSet {
    protected static final ThreadLocal<Map<String, String>> LOCAL_PAGE = new ThreadLocal();

    public UserInfoLocalSet() {
    }

    public static void setData(String key, String value, boolean isBase64) {
        if (isBase64) {
            value = EncryptUtils.encrypt(value);
        }

        if (LOCAL_PAGE.get() == null) {
            Map<String, String> data = new HashMap();
            data.put(key, value);
            LOCAL_PAGE.set(data);
        } else {
            Map<String, String> data = (Map) LOCAL_PAGE.get();
            data.put(key, value);
            LOCAL_PAGE.set(data);
        }

    }

    public static String get(String key) {
        if (LOCAL_PAGE.get() == null) {
            return null;
        } else {
            Map<String, String> data = (Map) LOCAL_PAGE.get();
            return (String) data.get(key);
        }
    }

    /**
     * 获取值的方法，传入写入时候的key即可
     *
     * @param args
     */
    public static void main(String[] args) {
        UserInfoLocalSet.get("key");
    }
}