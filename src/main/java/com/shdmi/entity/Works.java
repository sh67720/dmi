package com.shdmi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shanghai on 2017/10/25.
 */
public class Works implements Serializable {

    private int id;
    private String name;//品牌名称
    private Byte category;//类别
    private Byte industry;//所属行业
    private String serviceContent;//服务内容
    private String brandIntroduction;//品牌介绍
    private Boolean homepageShow = new Boolean(false); //是否在首页显示
    private String remark;//其他
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    private String coverPic;//封面图片
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

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
    }

    public Byte getIndustry() {
        return industry;
    }

    public void setIndustry(Byte industry) {
        this.industry = industry;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getBrandIntroduction() {
        return brandIntroduction;
    }

    public void setBrandIntroduction(String brandIntroduction) {
        this.brandIntroduction = brandIntroduction;
    }

    public Boolean getHomepageShow() {
        return homepageShow;
    }

    public void setHomepageShow(Boolean homepageShow) {
        this.homepageShow = homepageShow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }
}
