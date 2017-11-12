package com.shdmi.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vincent on 16/3/22.
 */
public enum ProjectBottomTag {
    SUBWAY("地铁", "#09f"),
    SCHOOL("学区", "#ff6162"),
    STORE("商铺", "#b15bff"),
    PART("公寓", "#00e3e3"),
    LIVE("住宅", "#e1e100"),
    HOUSE("别墅", "#9f4d95"),
    BRAND("品牌开发商", "#fe7242"),
    ;

    ProjectBottomTag(String name, String color){
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
        for (ProjectBottomTag projectBottomTag : values()) {
            map.put(projectBottomTag.getName(), projectBottomTag.getName());
        }
        return map;
    }

    public static Map<String, String> getColorMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (ProjectBottomTag projectBottomTag : values()) {
            map.put(projectBottomTag.getName(), projectBottomTag.getColor());
        }
        return map;
    }
}
