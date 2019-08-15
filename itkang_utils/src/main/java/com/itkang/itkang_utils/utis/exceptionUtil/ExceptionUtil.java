package com.itkang.itkang_utils.utis.exceptionUtil;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;


/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/7/30 20:17
 * @explain  Exception 工具类
 */
public class ExceptionUtil implements Serializable {

	/**
	 * Description：获取异常的堆栈信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		try {

			t.printStackTrace(pw);
			return sw.toString();

		} finally {
			pw.close();
		}
	}
}
