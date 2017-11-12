package com.shdmi.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vincent on 16/3/22.
 */
public enum IndustryType {
    TYPE1((byte)1, "金融 银行 投资"),
    TYPE2((byte)2, "食品 饮料 酒水"),
    TYPE3((byte)3, "服装 家纺 鞋帽"),
    TYPE4((byte)4, "工业 制造 交通"),
    TYPE5((byte)5, "科技 互联网 电商"),
    TYPE6((byte)6, "医药 美妆 母婴"),
    TYPE7((byte)7, "地产 酒店 建材"),
    TYPE8((byte)8, "教育 文化 培训"),
    TYPE9((byte)9, "家居 家电 日用")
    ;

    IndustryType(Byte code, String name){
        this.code = code;
        this.name = name;
    }

    private Byte code;
    private String name;

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

    public static IndustryType getByName(String name) {
        if (name == null || name.equals("")) return null;
        for (IndustryType type : values()) {
            if (name.equals(type.getName()))
                return type;
        }
        return null;
    }

    public static IndustryType getByCode(Byte code) {
        if (code == null || code.equals("")) return null;
        for (IndustryType type : values()) {
            if (code.equals(type.getCode()))
                return type;
        }
        return null;
    }

    public static Map<String, String> getNameMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (IndustryType industryType : values()) {
            map.put(industryType.getName(), industryType.getName());
        }
        return map;
    }
}
