package com.mutong.mtcommunity.kafka;

import com.alibaba.fastjson.JSONObject;
import com.mutong.mtcommunity.model.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-25 8:39
 */
@Component
public class EventProducer {

    @Resource
    private KafkaTemplate kafkaTemplate;

    public void fireEvent(Event event){
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
