package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.LoginTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-06-22 9:23
 */

@Repository
@Mapper
public interface LoginTicketMapper {

    void insertLoginTicket(LoginTicket loginTicket);

    LoginTicket findLoginTicket(String ticket);

    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}
