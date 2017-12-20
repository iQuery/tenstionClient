package com.imp.model;

import com.imp.system.Column;
import com.imp.system.Table;

@Table("tbl_car")
public class CarModel {

    @Column
    private Integer  id;
    @Column
    private String   cardNo;
    @Column
    private String   carNo;
    @Column
    private String   driver;
    @Column
    private String   remark;
    @Column
    private String   createTime;

    private Integer projectId;

    private Integer inOutType;

    private String   updateTime;




    @Override
    public String toString() {
        return "CarModel [id=" + id + ", cardNo=" + cardNo + ", carNo=" + carNo + ", driver=" + driver + ", remark="
                + remark + ", createTime=" + createTime + ", projectId=" + projectId + ", inOutType=" + inOutType
                + ", updateTime=" + updateTime + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getInOutType() {
        return inOutType;
    }

    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}



