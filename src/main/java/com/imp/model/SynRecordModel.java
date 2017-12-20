package com.imp.model;

import com.imp.system.Column;
import com.imp.system.Table;

@Table("tbl_syn_record")
public class SynRecordModel {

    @Column
    private Integer id;
    @Column
    private String json;
    @Column
    private String result;
    @Column
    private Integer maxTensionId;
    @Column
    private String createTime;
    @Column
    private String synTime;
    @Column
    private String updateTime;
    @Column
    private String beanNO;
    @Column
    private Integer flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getMaxTensionId() {
        return maxTensionId;
    }

    public void setMaxTensionId(Integer maxTensionId) {
        this.maxTensionId = maxTensionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSynTime() {
        return synTime;
    }

    public void setSynTime(String synTime) {
        this.synTime = synTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBeanNO() {
        return beanNO;
    }

    public void setBeanNO(String beanNO) {
        this.beanNO = beanNO;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
