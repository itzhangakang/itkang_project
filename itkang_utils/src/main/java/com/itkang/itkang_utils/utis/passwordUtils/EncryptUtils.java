package com.itkang.itkang_utils.utis.passwordUtils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/10/26 9:31
 * @explain  Java加密解密 -Jasypt
 */
public class EncryptUtils {

    /**
     * springMVC 项目中：
     * 引入依赖：
     *      <dependency>
     *       <groupId>com.github.ulisesbocchio</groupId>
     *        <artifactId>jasypt-spring-boot-starter</artifactId>
     *        <version>2.1.1</version>
     *      </dependency>
     *
     *      需要在WEB-INF中datasources.xml 中配置启动的bean去加载 EncrypConfig配置
     */

    /**
     * springBoot 项目中：
     * 引入依赖：
     *  <dependency>
     *   <groupId>com.github.ulisesbocchio</groupId>
     *    <artifactId>jasypt-spring-boot-starter</artifactId>
     *    <version>2.1.1</version>
     *  </dependency>
     *
     * 可以直接在application.properties文件中配置KEY
     * 例如:
     * #key
     * jasypt.encryptor.password = EbfYkitulv73I2p0mXI50JMXoaxZTKJ7
     *
     * 通过EncryptUtils工具类将需要加密的 密码加密后配置在application.properties文件中
     * 规则：必须用放入ENC()
     * 例如：spring.redis.password=ENC(4frlGp7f1xQEDWrxyCPkKg==)
     */
    // 密钥
    private static final String KEY = "EbfYkitulv73I2p0mXI50JMXoaxZTKJ7";

    public static void main(String[] args) {
        String ciphertext1 = encrypt("zaq123"); // K6NaSWdsTcJftZyBF1YYtg==
        System.out.println(ciphertext1);
        String ciphertext2 = encrypt("abcdefg"); // NRzvZb8tvlaZfEAhsGe89w==
        System.out.println(ciphertext2);

        String text1 = decrypt("K6NaSWdsTcJftZyBF1YYtg==");
        String text2 = decrypt("NRzvZb8tvlaZfEAhsGe89w==");
        System.out.println(text1);               // zaq123
        System.out.println(text2);               // abcdefg
    }

    /**
     * 加密
     * @param text 明文
     * @return     密文
     */
    public static String encrypt(String text) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.encrypt(text);
    }

    /**
     * 解密
     * @param ciphertext 密文
     * @return           明文
     */
    public static String decrypt(String ciphertext) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.decrypt(ciphertext);
    }

}
