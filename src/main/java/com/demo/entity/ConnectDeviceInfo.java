package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 27259 on 2017/3/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectDeviceInfo {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "SerialNumber")
    private String serialNumber;
    // 设备平台(Android/ios)
    @JsonProperty(value = "Platform")
    private String platform;
    @JsonProperty(value = "Brand")
    private String brand;
    @JsonProperty(value = "Model")
    private String model;
    @JsonProperty(value = "ReleaseVersion")
    private String releaseVersion;
    @JsonProperty(value = "SdkVersion")
    private Integer sdkVersion;
    @JsonProperty(value = "ABI")
    private String ABI;
    @JsonProperty(value = "ScreenWidth")
    private Integer screenWidth;
    @JsonProperty(value = "ScreenHeight")
    private Integer screenHeight;
    @JsonProperty(value = "Comment")
    private String comment;
    @JsonProperty(value = "PopularRank")
    private Integer popularRank;
    @JsonProperty(value = "Status")
    private Integer status;
    @JsonProperty(value = "ErrorMessage")
    private String errorMessage;
    @JsonProperty(value = "Description")
    private String description;
    @JsonProperty(value = "AdbPort")
    private String adbPort;
    @JsonProperty(value = "SocketPort")
    private Integer socketPort;
    @JsonProperty(value = "UserID")
    private String userID;
    @JsonProperty(value = "TransportMode")
    private String transportMode;
    @JsonProperty(value = "CreatedAt")
    private String createdAt;
    @JsonProperty(value = "UpdatedAt")
    private String updatedAt;

    @JsonProperty(value = "ScreenshotPort")
    private String screenshotPort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public Integer getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(Integer sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getABI() {
        return ABI;
    }

    public void setABI(String ABI) {
        this.ABI = ABI;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPopularRank() {
        return popularRank;
    }

    public void setPopularRank(Integer popularRank) {
        this.popularRank = popularRank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdbPort() {
        return adbPort;
    }

    public void setAdbPort(String adbPort) {
        this.adbPort = adbPort;
    }

    public Integer getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(Integer socketPort) {
        this.socketPort = socketPort;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
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

    public String getScreenshotPort() {
        return screenshotPort;
    }

    public void setScreenshotPort(String screenshotPort) {
        this.screenshotPort = screenshotPort;
    }
}
