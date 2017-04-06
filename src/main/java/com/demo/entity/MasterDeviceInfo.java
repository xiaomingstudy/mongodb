package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 27259 on 2017/3/25.
 */
//自动化设备（Android实体机 eg:我的oppor9s）
public class MasterDeviceInfo {
    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "Status")
    private String status;

    @JsonProperty(value = "Brand")
    private String brand;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "API")
    private String API;

    @JsonProperty(value = "Platform")
    private String platform;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "ScreenResolution")
    private String screenResolution;

    @JsonProperty(value = "FreeIn")
    private Integer freeIn;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public Integer getFreeIn() {
        return freeIn;
    }

    public void setFreeIn(Integer freeIn) {
        this.freeIn = freeIn;
    }
}
