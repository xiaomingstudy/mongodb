package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 27259 on 2017/3/23.
 */
@Entity
@Table(name = "case_step_result")
public class CaseStepResult extends IdEntity{

    private Integer caseId;
    private Integer caseStepId;
    private Integer caseResultId;
    private Integer stepNum;
    private String stepDesc;
    private String expectedResult;
    private String actualResult;
    private Integer testResult;
    private String errorMessage;
    private Integer testAttachmentId;

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseStepId() {
        return caseStepId;
    }

    public void setCaseStepId(Integer caseStepId) {
        this.caseStepId = caseStepId;
    }

    public Integer getCaseResultId() {
        return caseResultId;
    }

    public void setCaseResultId(Integer caseResultId) {
        this.caseResultId = caseResultId;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getTestAttachmentId() {
        return testAttachmentId;
    }

    public void setTestAttachmentId(Integer testAttachmentId) {
        this.testAttachmentId = testAttachmentId;
    }
}
