package com.csb.common.base;

/**
 * Created by Administrator on 2017/3/29.
 * 基本类
 * 用于生成bootstrap树
 */
public class BaseTree {
    private String id;
    private String text;
    private String parentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
