package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 27259 on 2017/3/25.
 */
@Entity
public class JobReal extends IdEntity {
    // 给定为安卓实体机
    private Integer deviceType;

    // 给定为web
    private Integer appType;

    // 提供给master的脚本存放路径
    private String scriptUrl;

    // 提供给master的app应用存放
    private String appUrl;

    // 提供给master的自动化执行结果的post路径地址
    private String resultUrl;

    // 提供给master的自动化系统的job开始执行后通知我们的url
    private String jobStatusUrl;

    // 需要跑自动化脚本的设备id
    private String deviceId;

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getScriptUrl() {
        return scriptUrl;
    }

    public void setScriptUrl(String scriptUrl) {
        this.scriptUrl = scriptUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    public String getJobStatusUrl() {
        return jobStatusUrl;
    }

    public void setJobStatusUrl(String jobStatusUrl) {
        this.jobStatusUrl = jobStatusUrl;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
