package com.demo.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by 27259 on 2017/4/6.
 */
public class StepResult {
    @JsonProperty(value = "ID")
    private String id;

    // 案例名称
    @JsonProperty(value = "Name")
    private String name;

    // 截图文件名字
    @JsonProperty(value = "Screenshot")
    private String screenShot;

    //	// 案例步骤执行状态
//	@JsonProperty(value = "status")
//	private String status;
//
    // 案例步骤执行错误信息
    @JsonProperty(value = "error_message")
    private String errorMessage;

    // 案例执行时间
    @JsonProperty(value = "CreateAt")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(String screenShot) {
        this.screenShot = screenShot;
    }

    //	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
