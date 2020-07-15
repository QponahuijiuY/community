package com.mutong.mtcommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 17:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    //点赞数
    private Integer likeCount;
    //收藏数
    private Integer collectionCount;


}
