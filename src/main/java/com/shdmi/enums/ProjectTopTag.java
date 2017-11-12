package com.shdmi.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vincent on 16/3/22.
 */
public enum ProjectTopTag {
    SELLING("在售", "#ff0000"),
    WAITSELL("待售", "#ff0000"),
    BLANK("毛坯", "#00a62f"),
    HARDBACK("精装", "#00a62f"),
    ;

    ProjectTopTag(String name, String color){
        this.name = name;
        this.color = color;
    }
    private String name;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<String, String> getNameMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (ProjectTopTag projectTopTag : values()) {
            map.put(projectTopTag.getName(), projectTopTag.getName());
        }
        return map;
    }

    public static Map<String, String> getColorMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (ProjectTopTag projectTopTag : values()) {
            map.put(projectTopTag.getName(), projectTopTag.getColor());
        }
        return map;
    }
}
