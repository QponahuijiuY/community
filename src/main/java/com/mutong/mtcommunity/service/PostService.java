package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.mapper.PostMapper;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.utils.SensitiveFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 17:43
 */
@Service
public class PostService {

    @Resource
    private SensitiveFilter sensitiveFilter;
    @Resource
    private PostMapper postMapper;

    public void insertPost(Post post){
        if (post == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        //转移HTML标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        //过滤敏感词
        post.setTitle(sensitiveFilter.filter(post.getTitle()));
        post.setContent(sensitiveFilter.filter(post.getContent()));
        postMapper.insertPost(post);
    }
}
