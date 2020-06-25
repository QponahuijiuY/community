package com.mutong.mtcommunity.model;

import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 17:35
 */
public class Post {
    //主键
    private Integer id;
    //用户id
    private Integer userId;
    //帖子标题
    private String title;
    //内容
    private String content;
    //分区专栏
    private Integer specialColumn;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modTime;
    //评论数
    private Integer commentCount;
    //浏览量
    private Integer pageView;
    //0 普通帖子 1 加精帖子 2 置顶帖子
    private Integer type;
    //0 删除 1 存在
    private Integer status;
    // 帖子分数
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSpecialColumn() {
        return specialColumn;
    }

    public void setSpecialColumn(Integer specialColumn) {
        this.specialColumn = specialColumn;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
