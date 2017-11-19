package com.shdmi.entity;

import java.util.Date;

/**
 * Created by user on 2017/11/18.
 */
public class SinglePic {

    private int id;
    private Byte type; //图片类型SinglePicType
    private String path; //图片路径
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
