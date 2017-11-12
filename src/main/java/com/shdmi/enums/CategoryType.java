package com.shdmi.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vincent on 16/3/22.
 */
public enum CategoryType {
    TYPE1((byte)1, "品牌全案"),
    TYPE2((byte)2, "LOGO（VI设计）"),
    TYPE3((byte)3, "产品创新开发"),
    TYPE4((byte)4, "营销（传播）")
    ;

    CategoryType(Byte code, String name){
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

    public static CategoryType getByName(String name) {
        if (name == null || name.equals("")) return null;
        for (CategoryType type : values()) {
            if (name.equals(type.getName()))
                return type;
        }
        return null;
    }

    public static CategoryType getByCode(Byte code) {
        if (code == null || code.equals("")) return null;
        for (CategoryType type : values()) {
            if (code.equals(type.getCode()))
                return type;
        }
        return null;
    }

    public static Map<String, String> getNameMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (CategoryType industryType : values()) {
            map.put(industryType.getName(), industryType.getName());
        }
        return map;
    }
}
