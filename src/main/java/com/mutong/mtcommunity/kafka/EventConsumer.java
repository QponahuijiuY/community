package com.mutong.mtcommunity.kafka;

import com.alibaba.fastjson.JSONObject;
import com.mutong.mtcommunity.model.Event;
import com.mutong.mtcommunity.model.Message;
import com.mutong.mtcommunity.model.Post;
import com.mutong.mtcommunity.service.ElasticSearchService;
import com.mutong.mtcommunity.service.MessageService;
import com.mutong.mtcommunity.service.PostService;
import com.mutong.mtcommunity.utils.CommunityConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-25 8:41
 */
@Component
public class EventConsumer implements CommunityConstant {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    @Resource
    private MessageService messageService;
    @Resource
    private PostService postService;
    @Resource
    private ElasticSearchService elasticSearchService;
    @KafkaListener(topics = {TOPIC_LIKE,TOPIC_COMMENT,TOPIC_FOLLOW,TOPIC_COLLECTION})
    public void handlerCommentMessage(ConsumerRecord record){
        if (record == null || record.value() == null){
            logger.error("消息内容为空");
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(),Event.class);
        if (event == null) {
            logger.error("消息格式错误");
            return;
        }

        Message message = new Message();
        message.setFromId(event.getUserId());
        message.setToId(event.getEntityUserId());
        message.setCreateTime(new Date());
        message.setContent(event.getEntityType());
        message.setStatus(0);
        message.setTopic(event.getTopic());
        message.setEntityId(event.getEntityId());
        messageService.addMessage(message);
    }

    //消费发帖事件
    @KafkaListener(topics = {TOPIC_PUBLISH})
    public void handlerPublicMessage(ConsumerRecord record){
        if (record == null || record.value() == null){
            logger.error("消息内容为空");
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(),Event.class);
        if (event == null){
            logger.error("消息格式错误");
            return;
        }

        Post post = postService.findPostById(event.getEntityId());
        elasticSearchService.savePost(post);
    }

    //消费删除帖子事件
    @KafkaListener(topics = {TOPIC_DELETE})
    public void handlerDeleteMessage(ConsumerRecord record){
        if (record == null || record.value() == null){
            logger.error("消息内容为空");
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(),Event.class);
        if (event == null){
            logger.error("消息格式错误");
            return;
        }
        elasticSearchService.deletePost(event.getEntityId());
    }
}
