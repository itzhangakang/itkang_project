package com.itkang.itkang_utils.utis.sms_短信;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2018/9/23 18:34
 *
 * 发送短信接口
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    /**
     * 需要在 application.properties 中配置参数
     * SMS_PRODUCT=Dysmsapi
     * SMS_DOMAIN=dysmsapi.aliyuncs.com
     * SMS_ACCESSKEYID=LTAIEVZKEgBUlSVJ
     * SMS_ACCESSKEYSECRET=tiUfFrfq8nFtAgIz3swGAIdkAtlZXR
     */

    @Value("${SMS_PRODUCT}")
    private String SMS_PRODUCT;

    @Value("${SMS_DOMAIN}")
    private String SMS_DOMAIN;

    @Value("${SMS_ACCESSKEYID}")
    private String SMS_ACCESSKEYID;

    @Value("${SMS_ACCESSKEYSECRET}")
    private String SMS_ACCESSKEYSECRET;

    // LTAIEVZKEgBUlSVJ    阿里云数据
    // tiUfFrfq8nFtAgIz3swGAIdkAtlZXR

    @RequestMapping(value = "/sendSms",method = RequestMethod.POST)
    public Map sendSme(String phone_number,String sign_name,String template_code,String template_param) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SMS_ACCESSKEYID, SMS_ACCESSKEYSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SMS_PRODUCT, SMS_DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);


        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone_number);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(sign_name);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(template_code);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(template_param);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse response = acsClient.getAcsResponse(request);

        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

        // 封装返回结果
        Map map = new HashMap();
        map.put("Code",response.getCode());
        map.put("Message",response.getMessage());
        map.put("RequestId",response.getRequestId());
        map.put("BizId",response.getBizId());

        return map;

    }

    /**
     * 查询明细接口
     */
    @RequestMapping(value = "sendDetail", method = RequestMethod.POST)
    public Map sendDetail(String phone_number, String bizId) throws Exception{

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SMS_ACCESSKEYID, SMS_ACCESSKEYSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SMS_PRODUCT, SMS_DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phone_number);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        //封装返回集

        return null;
    }

    // 测试发送短信
    public static void main(String[] args) throws Exception {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建http POST请求
        HttpPost httpPost = new HttpPost("http://localhost:8088/sms/sendSms");

        // 设置2个post参数，一个是scope、一个是q
        List<NameValuePair> parameters = new ArrayList<>(0);
        parameters.add(new BasicNameValuePair("phone_number", "18738613914"));
        parameters.add(new BasicNameValuePair("sign_name", "短信模板"));
        //  SMS_145599989  只有code的 模板 SMS_145910124 带name 模板
        parameters.add(new BasicNameValuePair("template_code", "SMS_145910124"));

        Map map = new HashMap();
        map.put("code", "123456");
        map.put("name", "123456");
        // 将map转成json数据
        String json = JSON.toJSONString(map);

        parameters.add(new BasicNameValuePair("template_param", json));

        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.err.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    }

}
