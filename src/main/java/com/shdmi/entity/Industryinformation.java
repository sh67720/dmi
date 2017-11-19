package com.shdmi.entity;

import java.util.Date;

/**
 * Created by user on 2017/11/19.
 */
public class Industryinformation {

    private int id;
    private String coverpic;//封面图片
    private String name;//资讯名称
    private String date;//资讯发布时间
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private int industryinformationId;//资讯ID
    private String path;//图片地址
    private Date picCreateTime;//图片创建时间
    private Date picUpdateTime;//图片更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getIndustryinformationId() {
        return industryinformationId;
    }

    public void setIndustryinformationId(int industryinformationId) {
        this.industryinformationId = industryinformationId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getPicCreateTime() {
        return picCreateTime;
    }

    public void setPicCreateTime(Date picCreateTime) {
        this.picCreateTime = picCreateTime;
    }

    public Date getPicUpdateTime() {
        return picUpdateTime;
    }

    public void setPicUpdateTime(Date picUpdateTime) {
        this.picUpdateTime = picUpdateTime;
    }
}
