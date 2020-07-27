package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-25 15:28
 */

@Repository
@Mapper
public interface MessageMapper {

    public void insertMassage(Message message) ;

    int selectMessageCountByToId(@Param("toId") int toId);

    List<Message> selectMessageByToId(@Param("toId") int toId,@Param("offset") int offset,@Param("limit") int limit);

    int updateStatusByid(@Param("id") int id,@Param("status") int status);

    int updateStatusByToId(@Param("toId") int toId,@Param("status") int status);
}
