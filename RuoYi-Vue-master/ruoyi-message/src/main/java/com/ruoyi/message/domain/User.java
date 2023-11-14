package com.ruoyi.message.domain;

public class User {
    private Integer id;
    private Integer   consumerid;
    private Integer departmentid;
    private Integer positionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsumerid() {
        return consumerid;
    }

    public void setConsumerid(Integer consumerid) {
        this.consumerid = consumerid;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }
}
