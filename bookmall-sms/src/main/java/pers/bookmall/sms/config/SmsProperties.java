package pers.bookmall.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bookmall.sms")
public class SmsProperties {

    Integer SDKAppID;

    String AppKey;

    String signName;

    String templateId;

    public Integer getSDKAppID() {
        return SDKAppID;
    }

    public void setSDKAppID(Integer SDKAppID) {
        this.SDKAppID = SDKAppID;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}