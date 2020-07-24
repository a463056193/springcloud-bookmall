package pers.bookmall.sms.listener;

import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import pers.bookmall.sms.config.SmsProperties;
import pers.bookmall.sms.demo.SendSms;
import pers.bookmall.sms.utils.SmsUtils;

import java.util.Map;

@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsListener {

    @Autowired
    private SendSms sendSms;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "bookmall.sms.queue", durable = "true"),
            exchange = @Exchange(value = "bookmall.sms.exchange",
                                 ignoreDeclarationExceptions = "true",
                                 type = ExchangeTypes.DIRECT),
            key = {"sms.verify.code"}))
    public void listenSms(Map<String, String> msg) throws Exception {
        if (msg == null || msg.size() <= 0) {
            // 放弃处理
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            // 放弃处理
            return;
        }
        // 发送消息
        System.out.println("接受到消息 " +  msg);
        this.sendSms.SendCode(phone, code);
        
    }
}