package pers.bookmall.sms.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.AddSmsTemplateRequest;
import com.tencentcloudapi.sms.v20190711.models.AddSmsTemplateResponse;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import pers.bookmall.sms.config.SmsProperties;

@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtils {

    @Autowired
    private SmsProperties prop;

    /*//产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";*/

    static final Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    public SendSmsResponse sendSms(String phone, String code, String signName, String templateID) throws TencentCloudSDKException {

        /* 必要步骤：
         * 实例化一个认证对象，入参需要传入腾讯云账户密钥对 secretId 和 secretKey
         * 本示例采用从环境变量读取的方式，需要预先在环境变量中设置这两个值
         * 您也可以直接在代码中写入密钥对，但需谨防泄露，不要将代码复制、上传或者分享给他人
         * CAM 密钥查询：https://console.cloud.tencent.com/cam/capi*/

        Credential cred = new Credential("AKIDWjiSr7B8LHxQl7oyLSItAHFVlngqnU1X", "RdjeMs9ADoCQP2Xlj8G7uMRS8YS2LTFp");

        // 实例化一个 http 选项，可选，无特殊需求时可以跳过
        HttpProfile httpProfile = new HttpProfile();
        /* SDK 默认使用 POST 方法
         * 如需使用 GET 方法，可以在此处设置，但 GET 方法无法处理较大的请求 */
        httpProfile.setReqMethod("POST");
        /* SDK 有默认的超时时间，非必要请不要进行调整
         * 如有需要请在代码中查阅以获取最新的默认值 */
        httpProfile.setConnTimeout(60);

        ClientProfile clientProfile = new ClientProfile();
        /* SDK 默认使用 TC3-HMAC-SHA256 进行签名
         * 非必要请不要修改该字段 */
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        /* 实例化 SMS 的 client 对象
         * 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量 */
        SmsClient client = new SmsClient(cred, "",clientProfile);
        /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
         * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
         * 属性可能是基本类型，也可能引用了另一个数据结构
         * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 */
        AddSmsTemplateRequest req = new AddSmsTemplateRequest();
        String templatename = "注册验证";
        req.setTemplateName(templatename);

        /* 模板内容 */
        String templatecontent     = "{1}为您的登录验证码，请于{2}分钟内填写，如非本人操作，请忽略本短信。";
        req.setTemplateContent    (templatecontent);

        /* 短信类型：0表示普通短信, 1表示营销短信 */
        Long smstype = 0L;
        req.setSmsType(smstype);

        /* 是否国际/港澳台短信：0：表示国内短信，1：表示国际/港澳台短信。 */
        Long international = 0L;
        req.setInternational(0L);

        /* 模板备注：例如申请原因，使用场景等 */
        String remark = "注册验证";
        req.setRemark(remark);

        //初始化acsClient,暂不支持region化
        /*IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                prop.getAccessKeyId(), prop.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);*/

        //组装请求对象-具体描述见控制台-文档部分内容

        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        String[] phoneNumbers = {"+8613168355383"};
        request.setPhoneNumberSet(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.setSign(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateID(templateID);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        String[] templateParams = {code};
        request.setTemplateParamSet(templateParams);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");



        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse res = client.SendSms(request);

        logger.info("发送短信Json：{}", SendSmsResponse.toJsonString(res));



        // 输出 JSON 格式的字符串回包
        System.out.println(SendSmsResponse.toJsonString(res));

        // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
        System.out.println(res.getRequestId());

        return res;
    }
}