package com.rong.cms.model;

public enum ChannelType {
    NAV_CHANNEL("导航栏目"),TOP_LIST("文章列表"),TOP_CONTENT("文章内容"),TOP_IMG("图片列表");
    private String name;

    ChannelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
