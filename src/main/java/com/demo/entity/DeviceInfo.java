package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 27259 on 2017/3/7.
 */
@Entity
@Table(name="device_list")
public class DeviceInfo extends IdEntity{
   //连接设备Id
   private String exDeviceId;

    //序列号
    private String serialNumber;

    //使用人
    private String userName;



    //设备型号（OPPO R9S）
    private String deviceType;

    //设备平台（android/ios）
    private String devicePlatform;

    //系统版本（android/ios）
    private String version;

  //设备厂商
    private String facturer;

  //设备状态（1可用，2维修中，3不可用（使用中））
    private Integer deviceStatus;

   //删除标志（0未删除，1删除）
    private String deleteFlag;

   //slave的Id
    private String SlaveId;

    //平台类型
    private Integer connectTypes;

  //设备像素宽度
    private Integer screenWidth;

   //设备像素高度
    private Integer screenHeight;

  //sdk版本号
    private Integer sdkVersion;

    //ADB端口
    private Integer adbPort;

    //ABI
    private String ABI;

    private Date insertTime;

    private User user;

    //设备所在的slave的IP
    private String slaveIp;

    //case和user多对一关联
    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

   public String getExDeviceId() {
        return exDeviceId;
    }

   public void setExDeviceId(String exDeviceId) {
       this.exDeviceId = exDeviceId;
   }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
       this.devicePlatform = devicePlatform;
    }

   public String getVersion() {
        return version;
    }

   public void setVersion(String version) {
       this.version = version;
   }

    public String getFacturer() {
       return facturer;
   }

   public void setFacturer(String facturer) {
       this.facturer = facturer;
    }

   public Integer getDeviceStatus() {
        return deviceStatus;
   }

    public void setDeviceStatus(Integer deviceStatus) {
       this.deviceStatus = deviceStatus;
   }


   public String getDeleteFlag() {
       return deleteFlag;
   }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
   }

    public String getSlaveId() {
       return SlaveId;
   }

   public void setSlaveId(String slaveId) {
       SlaveId = slaveId;
   }

   public Integer getConnectTypes() {
       return connectTypes;
   }

   public void setConnectTypes(Integer connectTypes) {
       this.connectTypes = connectTypes;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public Integer getAdbPort() {
        return adbPort;
    }

    public void setAdbPort(Integer adbPort) {
        this.adbPort = adbPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSlaveIp() {
        return slaveIp;
    }

    public void setSlaveIp(String slaveIp) {
        this.slaveIp = slaveIp;
    }
}
