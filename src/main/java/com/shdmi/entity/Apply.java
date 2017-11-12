package com.shdmi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vincent on 16/3/20.
 */
public class Apply implements Serializable {
    private int id;
    private byte type; //类型(0.领取资料;1.降价通知;2.优惠报名;3.获取地址;4.预约看房)
    private int projectId; //项目id
    private String name; //姓名
    private String phone; //电话
    private String address; //地址
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    private String title;
    private String subTitle;
    private String telphone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
