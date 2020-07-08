package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.mapper.ColumnMapper;
import com.mutong.mtcommunity.model.Column;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-08 11:13
 */
@Service
public class ColumnService {
    @Resource
    private ColumnMapper columnMapper;
    public Column findSpecialColumn(Integer id) {
        return columnMapper.selectColumnById(id);
    }
}
