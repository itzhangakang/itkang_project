package com.itkang.itkang_utils.utis.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    /**
     * 发送邮件
     * 参数一:发送邮件给谁
     * 参数二:发送邮件的内容
     */
    public static void sendMail(String toEmail, String emailMsg) throws Exception {
        //1_创建Java程序与163邮件服务器的连接对象
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.auth", "true");
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("linix_kang", "linxi123");
            }
        };
        Session session = Session.getInstance(props, auth);
        //2_创建一封邮件
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("linix_kang@163.com"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("用户激活");
        message.setContent(emailMsg, "text/html;charset=UTF-8");
        //3_发送邮件
        Transport.send(message);
    }
    /**
     * 测试类
     */
    public static void main(String[] args) throws Exception{
        String toEmail = "linix_kang@163.com";
        String emailMsg = "<a href='http://localhost:8080/user?action=active&codeuser.getCode()'>用户激活admin</a>";
        sendMail(toEmail,emailMsg);
        System.out.println("发送成功。。。");
    }


}
