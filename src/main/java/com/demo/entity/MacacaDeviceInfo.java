package com.demo.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class MacacaDeviceInfo {
    @JsonProperty(value = "_id")
    private String id;
    @JsonProperty(value = "screenWidth")
    private String screenWidth;
    @JsonProperty(value = "screenHeight")
    private String screenHeight;
    @JsonProperty(value = "abi")
    private String ABI;
    @JsonProperty(value = "sdkVersion")
    private String sdkVersion;
    @JsonProperty(value = "releaseVersion")
    private String releaseVersion;
    @JsonProperty(value = "brand")
    private String brand;
    @JsonProperty(value = "model")
    private String model;
    @JsonProperty(value = "serialNumber")
    private String serialNumber;
    // 设备平台(Android/ios)
    @JsonProperty(value = "plantForm")
    private String plantform;
    @JsonProperty(value = "status")
    private Integer status;
    @JsonProperty(value = "created_at")
    private String createdAt;
    @JsonProperty(value = "updated_at")
    private String updatedAt;

    @JsonProperty(value = "slaveId")
    private String slaveId;

    @JsonProperty(value = "slaveIP")
    private String slaveIP;

    @JsonProperty(value = "__v")
    private String no;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getScreenWidth() {
        return screenWidth;
    }
    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }
    public String getScreenHeight() {
        return screenHeight;
    }
    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }
    public String getABI() {
        return ABI;
    }
    public void setABI(String aBI) {
        ABI = aBI;
    }
    public String getSdkVersion() {
        return sdkVersion;
    }
    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }
    public String getReleaseVersion() {
        return releaseVersion;
    }
    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
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
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getPlantform() {
        return plantform;
    }
    public void setPlantform(String plantform) {
        this.plantform = plantform;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(String slaveId) {
        this.slaveId = slaveId;
    }


    public String getSlaveIP() {
        return slaveIP;
    }
    public void setSlaveIP(String slaveIP) {
        this.slaveIP = slaveIP;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }

}
