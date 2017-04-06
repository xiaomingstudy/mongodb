package com.demo.entity;

/**
 * Created by 27259 on 2017/4/6.
 */
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
public class JobResult {

    @JsonProperty(value = "JobID")
    private String jobId;

    // 案例状态
    @JsonProperty(value = "Status")
    private String status;

    // 错误信息
    @JsonProperty(value = "ErrorMessage")
    private String errorMessage;

    // 设备日志文件名称
    @JsonProperty(value = "DeviceLog")
    private String deviceLog;

    // job日志文件名称
    @JsonProperty(value = "JobLog")
    private String jobLog;

    // job创建时间
    @JsonProperty(value = "CreatedAt")
    private String CreatedAt;

    // job开始运行时间
    @JsonProperty(value = "RunAt")
    private String RunAt;

    // job结束时间
    @JsonProperty(value = "EndAt")
    private String EndAt;

    // 案例步骤执行结果
    @JsonProperty(value = "Steps")
    private List<StepResult> steps;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDeviceLog() {
        return deviceLog;
    }

    public void setDeviceLog(String deviceLog) {
        this.deviceLog = deviceLog;
    }

    public String getJobLog() {
        return jobLog;
    }

    public void setJobLog(String jobLog) {
        this.jobLog = jobLog;
    }

    public List<StepResult> getSteps() {
        return steps;
    }

    public void setSteps(List<StepResult> steps) {
        this.steps = steps;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getRunAt() {
        return RunAt;
    }

    public void setRunAt(String runAt) {
        RunAt = runAt;
    }

    public String getEndAt() {
        return EndAt;
    }

    public void setEndAt(String endAt) {
        EndAt = endAt;
    }
}
