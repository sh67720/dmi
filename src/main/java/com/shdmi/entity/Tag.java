package com.shdmi.entity;

import java.io.Serializable;

/**
 * Created by vincent on 16/3/20.
 */
public class Tag implements Serializable {
    private int id;
    private byte type; //标签类型(0.上方;1.下方)
    private int projectId; //项目id
    private String name; //标签名称
    private int sort;   //排序

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
