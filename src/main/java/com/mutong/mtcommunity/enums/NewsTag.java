package com.mutong.mtcommunity.enums;

/**
 * 资讯新闻 tag
 */
public enum NewsTag {
    ENT(0,"ent"), //娱乐
    SOCTETY(1,"society"), //社会
    CHINA(2,"china"), //国内
    WORLD(3,"world"), //国外
    LIFE(4,"life"),//生活
    LAW(5,"law"),//法治
    TECH(6,"tech");//科技
    private int tag;
    private String message;

    NewsTag(int tag, String message) {
        this.tag = tag;
        this.message = message;
    }
    NewsTag(String message) {
        this.message = message;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
