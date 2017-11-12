package com.shdmi.entity;

import java.io.Serializable;

/**
 * Created by vincent on 16/3/20.
 */
public class Picture implements Serializable {
    private int id;
    private byte type; //图片类型(0.上方;1.下方)
    private int projectId; //项目id
    private String path; //图片路径

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}
