package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 27259 on 2017/4/3.
 */
@Entity
@Table(name="button_list")
public class Button extends IdEntity {
    private String buttonName;

    private Integer roleFlag;

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public Integer getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }
}
