package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.mapper.MessageMapper;
import com.mutong.mtcommunity.model.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-25 8:42
 */
@Service
public class MessageService {

    @Resource
    private MessageMapper messageMapper;
    public void addMessage(Message message) {
        messageMapper.insertMassage(message);
    }

    public int selectMessageCountByToId(int toId){
        return messageMapper.selectMessageCountByToId(toId);
    }


    public List<Message> selectMessageByToId(int id) {
        return messageMapper.selectMessageByToId(id);
    }

    public int deleteCommentById(int id, int i) {
        return messageMapper.updateStatusByid(id,i);
    }

    public int deleteCommentAll(int toId,int i) {
        return messageMapper.updateStatusByToId(toId, i);
    }
}
