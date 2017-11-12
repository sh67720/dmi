package com.shdmi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vincent on 16/3/20.
 */
public class Project implements Serializable {
    private int id;
    private String mail; //邮箱
    private String title; //项目标题
    private String subTitle; //项目子标题
    private String feature; //项目特点
    private Double price; //参考均价
    private String location; //楼盘位置
    private String preferential; //楼盘优惠
    private String news; //最新动态
    private String remark; //项目简介
    private String area; //所在区域
    private String subArea; //区域板块
    private String acreage; //规划面积
    private String buildAcreage; //建筑面积
    private String buildType; //建筑形式
    private String plotRatio; //容积率
    private String greenRatio; //绿化率
    private String propertyRight; //产权年限
    private String fitment; //装修情况
    private String developer; //开发商
    private String mainHouseType; //主力户型
    private String traffic; //交通出行
    private String supporting; //周边配套
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private String telphone; //联系电话
    private String telphone1; //联系电话

    private String[] topTags;
    private int[] topTagSorts;
    private String[] bottomTags;
    private int[] bottomTagSorts;

    private String[] topPictures;
    private String[] bottomPictures;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getBuildAcreage() {
        return buildAcreage;
    }

    public void setBuildAcreage(String buildAcreage) {
        this.buildAcreage = buildAcreage;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getFitment() {
        return fitment;
    }

    public void setFitment(String fitment) {
        this.fitment = fitment;
    }

    public String getGreenRatio() {
        return greenRatio;
    }

    public void setGreenRatio(String greenRatio) {
        this.greenRatio = greenRatio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMainHouseType() {
        return mainHouseType;
    }

    public void setMainHouseType(String mainHouseType) {
        this.mainHouseType = mainHouseType;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(String plotRatio) {
        this.plotRatio = plotRatio;
    }

    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPropertyRight() {
        return propertyRight;
    }

    public void setPropertyRight(String propertyRight) {
        this.propertyRight = propertyRight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSupporting() {
        return supporting;
    }

    public void setSupporting(String supporting) {
        this.supporting = supporting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String[] getBottomTags() {
        return bottomTags;
    }

    public void setBottomTags(String[] bottomTags) {
        this.bottomTags = bottomTags;
    }

    public String[] getTopTags() {
        return topTags;
    }

    public void setTopTags(String[] topTags) {
        this.topTags = topTags;
    }

    public String[] getBottomPictures() {
        return bottomPictures;
    }

    public void setBottomPictures(String[] bottomPictures) {
        this.bottomPictures = bottomPictures;
    }

    public String[] getTopPictures() {
        return topPictures;
    }

    public void setTopPictures(String[] topPictures) {
        this.topPictures = topPictures;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
        this.telphone1 = telphone.replace("-", "");
    }

    public String getTelphone1() {
        return telphone1;
    }

    public void setTelphone1(String telphone1) {
        this.telphone1 = telphone1;
    }

    public int[] getBottomTagSorts() {
        return bottomTagSorts;
    }

    public void setBottomTagSorts(int[] bottomTagSorts) {
        this.bottomTagSorts = bottomTagSorts;
    }

    public int[] getTopTagSorts() {
        return topTagSorts;
    }

    public void setTopTagSorts(int[] topTagSorts) {
        this.topTagSorts = topTagSorts;
    }
}
