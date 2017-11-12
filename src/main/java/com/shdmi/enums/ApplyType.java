package com.shdmi.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vincent on 16/3/23.
 */
public enum ApplyType {
    LQZL((byte)0, "免费获取楼盘资料", "立即获取", "请准确填写收件信息，我们将通过快递的方式免费邮寄给您。", true),
    JJTZ((byte)1, "降价通知我", "立即预约", "请填写准确的手机号码，以便在楼盘价格以及优惠变动的第一时间通知您。", false),
    YHBM((byte)2, "报名团房最低价", "立即预约", "报名后您才可享受售楼处团购独家优惠哦！", false),
    HQDZ((byte)3, "免费获取地址路线", "立即获取", "点击立即获取即可免费获取项目详细地址及路线", false),
    YYKF((byte)4, "预约看房", "立即预约", "报名后您才可享受售楼处团购独家优惠哦！", false),
    ;

    public static Map<Byte, ApplyType> getApplyTypeMap() {
        Map<Byte, ApplyType> map = new LinkedHashMap<>();
        for (ApplyType applyType : values()) {
            map.put(applyType.getType(), applyType);
        }
        return map;
    }

    ApplyType(byte type, String title, String button, String desc, boolean hasAddr) {
        this.button = button;
        this.desc = desc;
        this.title = title;
        this.type = type;
        this.hasAddr = hasAddr;
    }

    private byte type;
    private String title;
    private String button;
    private String desc;
    private boolean hasAddr;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public boolean isHasAddr() {
        return hasAddr;
    }

    public void setHasAddr(boolean hasAddr) {
        this.hasAddr = hasAddr;
    }
}
