package pers.bookmall.sms;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.bookmall.sms.demo.SendSms;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testCode {

    @Autowired
    private SendSms sendSms;

    @Test
    public void test() throws TencentCloudSDKException {
        this.sendSms.SendCode("13119558980","123456");
    }

}
