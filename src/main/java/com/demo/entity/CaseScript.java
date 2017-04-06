package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 27259 on 2017/3/20.
 */
@Entity
@Table(name = "case_script")
public class CaseScript extends IdEntity {
    private String scriptPath;
    private Long caseId;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }
}
