package com.shdmi.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单纯图片的类型
 * Created by ShenHua on 2017/11/18.
 */
public enum SinglePicType {
    /*BANNERBIG((byte)1, "轮播大图", "图片尺寸250*120"),
    BANNERSMALL((byte)2, "轮播小图", "图片尺寸250*120"),*/
    COOPERATEMS((byte)3, "一起创新大牌", "图片尺寸250*120"),
    ;

    SinglePicType(Byte code, String name, String tips){
        this.code = code;
        this.name = name;
        this.tips = tips;
    }

    private Byte code;
    private String name;
    private String tips;

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public static SinglePicType getByName(String name) {
        if (name == null || name.equals("")) return null;
        for (SinglePicType type : values()) {
            if (name.equals(type.getName()))
                return type;
        }
        return null;
    }

    public static SinglePicType getByCode(Byte code) {
        if (code == null || code.equals("")) return null;
        for (SinglePicType type : values()) {
            if (code.equals(type.getCode()))
                return type;
        }
        return null;
    }

    public static Map<String, String> getNameMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (SinglePicType singlePicType : values()) {
            map.put(singlePicType.getName(), singlePicType.getName());
        }
        return map;
    }
}
