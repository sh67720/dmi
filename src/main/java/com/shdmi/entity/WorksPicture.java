package com.shdmi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shanghai on 2017/10/25.
 */
public class WorksPicture implements Serializable {

    private int id;
    private int workcaseId;//品牌ID
    private String path;//图片地址
    private Boolean iscover = new Boolean(false);//是否是封面图片
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkcaseId() {
        return workcaseId;
    }

    public void setWorkcaseId(int workcaseId) {
        this.workcaseId = workcaseId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIscover() {
        return iscover;
    }

    public void setIscover(Boolean iscover) {
        this.iscover = iscover;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
