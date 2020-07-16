package com.mutong.mtcommunity.service;

import com.mutong.mtcommunity.dto.ResultTypeDTO;
import com.mutong.mtcommunity.enums.CustomizeErrorCode;
import com.mutong.mtcommunity.enums.ScoreType;
import com.mutong.mtcommunity.mapper.UserMapper;
import com.mutong.mtcommunity.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @Author: gengchen.jing@yoyi.com.cn
 * @Date: 2020-07-16 9:21
 */
@Service
public class SignInService extends RedisKeyUtil {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserMapper userMapper;


    public ResultTypeDTO signIn(int id) {
        if (id <= 0) {
            new ResultTypeDTO().errorOf(CustomizeErrorCode.USER_ID_EMPTY);
        }
        String signInDayKey = getSignInDay(new Date());
        Boolean isSigined = isSigined(id);
        if(isSigined){
            return new ResultTypeDTO().errorOf(CustomizeErrorCode.ALREADY_SIGIN);
        }else{
            redisTemplate.opsForSet().add(signInDayKey,id);
            this.redisTemplate.expire(signInDayKey,getRefreshTime(), TimeUnit.SECONDS);
            increaseScore(id, ScoreType.SIGN_IN);//给用户增加积分
        }
        return new ResultTypeDTO().okOf();




    }

    private void increaseScore(Integer id, ScoreType signIn) {
        userMapper.increaseScore(id,signIn.getType());
    }
    public boolean isSigined(int id) {
        String signInDayKey = getSignInDay(new Date());
        Boolean sismember = redisTemplate.opsForSet().isMember(signInDayKey, id);
        return sismember;
    }
    public static int getRefreshTime(){
        Calendar calendar = Calendar.getInstance();
        int now = (int) (calendar.getTimeInMillis()/1000);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY , 0);
        return (int) (calendar.getTimeInMillis()/1000-now);
    }
}
