package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Comment;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.service.CommentService;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.utils.CommunityConstant;
import com.mutong.mtcommunity.utils.CommunityUtil;
import com.mutong.mtcommunity.utils.HostHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-24 10:27
 */
@Controller
public class CommentController implements CommunityConstant {


    @Resource
    private HostHolder hostHolder;
    @Resource
    private CommentService commentService;
    @Resource
    private PostService postService;
    @PostMapping(value = "/comment/add/{postId}")
    public String addComment(@PathVariable("postId")int postId, Comment comment) {
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());

        commentService.addComment(comment);
        //触发评论事件

        if (comment.getEntityType() == ENTITY_TYPE_POST) {
            Post target = postService.findPostById(comment.getEntityId());
//            event.setEntityUserId(target.getUserId());
        } else if (comment.getEntityType() == ENTITY_TYPE_COMMENT) {
            Comment target = commentService.findCommentById(comment.getEntityId());
//            event.setEntityUserId(target.getUserId());
        }
//        eventProducer.fireEvent(event);

        if (comment.getEntityType() == ENTITY_TYPE_POST) {

        }

        return "redirect:/detail/" + postId +"#comment";
    }
    @PostMapping("/comment/delete/{id}")
    @ResponseBody
    public String deleteComment(@PathVariable("id") int id){
        int row = commentService.deleteCommentById(id,1);
        if (row > 0){
            return CommunityUtil.getJSONString(0,"操作成功!");
        }else {
            return CommunityUtil.getJSONString(1,"删除失败");
        }
    }
}
