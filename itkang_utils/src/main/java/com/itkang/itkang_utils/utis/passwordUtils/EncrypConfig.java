package com.itkang.itkang_utils.utis.passwordUtils;

import com.itkang.itkang_utils.utis.passwordUtils.HexUtils.AesUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 在springMVC中使用 Jasypt 加密解密方式
 */
public class EncrypConfig extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		/**
		 * 读取application.properties 配置文件中已经加密过的参数
		 */
		String dbPassword = props.getProperty("jdbc.password").toString();
		String redisPassword = props.getProperty("spring.redis.password").toString();
		String ftpPassword = props.getProperty("ftp.ftpLoginPwd").toString();
		try {
			/**
			 * 通过与加密方式相同的解密算法来解密，解密后再次写入
			 */
			dbPassword = AesUtil.aesDecrypt(dbPassword,AesUtil.KEY);
			redisPassword =  AesUtil.aesDecrypt(redisPassword,AesUtil.KEY);
			ftpPassword =  AesUtil.aesDecrypt(ftpPassword,AesUtil.KEY);
			props.put("jdbc.password",dbPassword);
			props.put("spring.redis.password",redisPassword);
			props.put("ftp.ftpLoginPwd",ftpPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.processProperties(beanFactoryToProcess, props);
	}
	
	
}
