package com.mutong.mtcommunity.controller;

import com.mutong.mtcommunity.model.Comment;
import com.mutong.mtcommunity.model.Message;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.CommentService;
import com.mutong.mtcommunity.service.MessageService;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.service.UserService;
import com.mutong.mtcommunity.utils.HostHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-25 18:23
 */
@Controller
public class MessageController {

    @Resource
    private HostHolder hostHolder;
    @Resource
    private MessageService messageService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    @Resource
    private CommentService commentService;



    @GetMapping("/message")
    public String message(Model model){
        User user = hostHolder.getUser();
        List<Message> lists = messageService.selectMessageByToId(user.getId());
        List<Map<String,Object>> list = new ArrayList<>();

        for (Message message : lists) {
            Map<String,Object> map = new HashMap<>();
            map.put("message",message);
            User fromUser = userService.findUserById(message.getFromId());
            map.put("fromUser",fromUser);
            String topic = message.getTopic();
            map.put("topic",topic);
            int content = message.getContent();
            map.put("content",content);
            //帖子
            if (content == 1){
//                int postId = message.getEntityId();
                Post post = postService.findPostById(message.getEntityId());
                map.put("post",post);
            }else {
//                int commentId = message.getEntityId();
                Comment comment = commentService.findCommentById(message.getEntityId());
                map.put("comment",comment);
            }

            map.put("createTime",message.getCreateTime());
            list.add(map);
        }
        model.addAttribute("user",user);
        model.addAttribute("list",list);
        return "user/message";
    }
}
