package com.mutong.mtcommunity.controller;

import cn.hutool.core.util.ObjectUtil;
import com.mutong.mtcommunity.model.Column;
import com.mutong.mtcommunity.model.Comment;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.model.User;
import com.mutong.mtcommunity.service.*;
import com.mutong.mtcommunity.utils.CommunityConstant;
import com.mutong.mtcommunity.utils.HostHolder;
import com.mutong.mtcommunity.utils.Page;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @description: 文章相关Controller
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 16:16
 */
@Controller
public class PostController implements CommunityConstant {
    @Resource
    private HostHolder hostHolder;

    @Resource
    private CommentService commentService;

    @Resource
    private PostService postService;

    @Resource
    private ColumnService columnService;

    @Resource
    private CollectionService collectionService;
    @Resource
    private LikeService likeService;

    @Resource
    private UserService userService;
    @GetMapping("/add")
    public String getAddPage(Model model){
        User user = hostHolder.getUser();
        if (ObjectUtil.isNull(user) || ObjectUtil.isEmpty(user)){
            model.addAttribute("loginMsg","你还没有登陆");
            return "redirect:/login";
        }
        return "jie/add";

    }

    @PostMapping("/add")
    public String add(@RequestParam("title") String title, @RequestParam("description") String content, @RequestParam("quiz") Integer specialColumn, Model model, HttpSession session,@RequestParam("code") String code){
        //从session里面获取验证码
        String kaptcha = (String) session.getAttribute("kaptcha");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "jie/add";
        }
        model.addAttribute("title",title);
        model.addAttribute("description",content);
        model.addAttribute("quiz",specialColumn);
        title = title.trim();
        User user = hostHolder.getUser();
        Post post = new Post();
        post.setUserId(user.getId());
        post.setSpecialColumn(specialColumn);
        post.setContent(content);
        post.setTitle(title);
        post.setCreateTime(new Date());
        post.setModTime(new Date());
        post.setPageView(0);
        post.setCommentCount(0);
        post.setType(0);
        post.setStatus(1);
        post.setScore(DEFAULT_SCORE);
        post.setLikeCount(0);
        post.setCollectionCount(0);
        postService.insertPost(post);

        return "redirect:/";
    }


    @PostMapping("/edit")
    public String edit(@RequestParam("id") int postId, @RequestParam("title") String title, @RequestParam("description") String content, @RequestParam("quiz") Integer specialColumn, Model model, HttpSession session, @RequestParam("code") String code){
        //从session里面获取验证码
        String kaptcha = (String) session.getAttribute("kaptcha");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "jie/edit";
        }
        model.addAttribute("title",title);
        model.addAttribute("description",content);
        model.addAttribute("quiz",specialColumn);
        title = title.trim();
        postService.updatePost(postId,title,content,specialColumn);
        return "redirect:/";

    }
    @Transactional
    @GetMapping("/detail/{postId}")
    public String getPost(@PathVariable("postId") int postId, Model model, Page page){

        //访问量加一
        postService.increasePageView(postId);
        //获取帖子信息
        Post post = postService.findPostById(postId);
        Column column = columnService.findSpecialColumn(post.getSpecialColumn());
        //获取这篇帖子作者信息
        User user = userService.findUserById(post.getUserId());
        boolean hasCollection = false;
        boolean hasLike = false;
        if (hostHolder.getUser() != null){
            hasCollection = collectionService.hasCollection(hostHolder.getUser().getId(), postId);
            hasLike = likeService.hasLike(hostHolder.getUser().getId(), postId);
        }
        long likePostKeySize = likeService.findLikePostKeySize(postId);

        List<Post> topCommentPost = postService.findPostByComment();
        page.setLimit(5);
        page.setPath("/discuss/detail/" + postId);
        page.setRows(post.getCommentCount());
        List<Comment> commentList = commentService.findCommentByEntity(ENTITY_TYPE_POST, post.getId(), page.getOffset(), page.getLimit());
        List<Map<String,Object>> commentVoList = new ArrayList<>();
        if (commentList != null){
            for (Comment comment : commentList) {
                Map<String,Object> commentVo = new HashMap<>();
                //获取评论信息
                commentVo.put("comment",comment);
                //获取评论作者
                User commentUser = userService.findUserById(comment.getUserId());
                commentVo.put("user",commentUser);
                //回复列表
                List<Comment> replyList = commentService.findCommentByEntity(ENTITY_TYPE_COMMENT, comment.getUserId(), 0, Integer.MAX_VALUE);

                //回复列表
                List<Map<String,Object>> replyVoList = new ArrayList<>();
                if (replyList != null){
                    for (Comment reply : replyList) {
                        Map<String,Object> replyVo = new HashMap<>();
                        //回复内容
                        replyVo.put("reply",reply);
                        //回复作者
                        User replyUser = userService.findUserById(reply.getUserId());
                        replyVo.put("user",replyUser);
                        //回复的目标
                        User targer = reply.getTargetId() == 0 ? null : userService.findUserById(reply.getTargetId());
                        replyVo.put("target",targer);
                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replys",replyVoList);

                int count = commentService.findCommentCount(ENTITY_TYPE_COMMENT,comment.getId());
                commentVo.put("replyCount",count);
                commentVoList.add(commentVo);
            }
        }
        model.addAttribute("comments",commentVoList);
        model.addAttribute("topCommentPost",topCommentPost);
        model.addAttribute("likePostKeySize",likePostKeySize);
        model.addAttribute("hasLike",hasLike);
        model.addAttribute("post",post);
        model.addAttribute("hasCollection",hasCollection);
        model.addAttribute("column",column);
        model.addAttribute("user",user);
        return "jie/detail";
    }


    @GetMapping("/add/{postId}")
    public String getEditPage(@PathVariable("postId")int postId,Model model){
        Post post = postService.findPostById(postId);
        if (hostHolder.getUser().getId() != post.getUserId()){
            return "other/500";
        }else{
            model.addAttribute("title",post.getTitle());
            model.addAttribute("description",post.getContent());
            model.addAttribute("id",post.getId());
        }
        return "jie/edit";
    }


}
