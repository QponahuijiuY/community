package com.mutong.mtcommunity.mapper;

import com.mutong.mtcommunity.model.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 11:14
 */
@Repository
@Mapper
public interface ColumnMapper {
    Column selectColumnById(@Param("id") Integer id);
}
