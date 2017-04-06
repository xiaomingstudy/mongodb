package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 27259 on 2017/3/25.
 */
@Entity
@Table(name="tbl_app_info")
public class AppInfo extends IdEntity{
    private String appNumber;
    private String appName;
    private String remark;
    private Integer appType;
    private String appPath;

    public String getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(String appNumber) {
        this.appNumber = appNumber;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }
}
