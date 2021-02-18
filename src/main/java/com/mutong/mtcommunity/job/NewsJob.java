package com.mutong.mtcommunity.job;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mutong.mtcommunity.enums.ExcepConstants;
import com.mutong.mtcommunity.enums.NewsTag;
import com.mutong.mtcommunity.mapper.NewsMapper;
import com.mutong.mtcommunity.model.News;
import com.mutong.mtcommunity.utils.CommonResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 新闻资讯job
 */
@Component
public class NewsJob {
    @Resource
    private NewsMapper newsMapper;
    public static void main(String[] args) throws IOException {
       new NewsJob().addSocietyNews(NewsTag.WORLD);
//       new NewsJob().GetSocietyNews("china");
//       new NewsJob().GetSocietyNews("world");
//       new NewsJob().GetSocietyNews("law");
//       new NewsJob().GetSocietyNews("ent");
//       new NewsJob().GetSocietyNews("tech");
//       new NewsJob().GetSocietyNews("life");
    }

    /**
     * 拉取新闻资讯
     * @return
     */
    public CommonResult addSocietyNews(NewsTag newsTag){
        String key = newsTag.getMessage();
        String url = "https://news.cctv.com/2019/07/gaiban/cmsdatainterface/page/"+key+"_1.jsonp?cb="+key;
        String res = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
        String response = res.substring(key.length() + 1, res.length() - 1);
        String str = (String) JSON.toJSON(response);
        JSONObject json = JSONObject.parseObject(str);
        JSONArray newsArray = json.getJSONObject("data").getJSONArray("list");
        System.out.println(newsArray);
        for (Object n : newsArray) {
            News news = new News();
            news.setTag(newsTag.getTag());
            JSONObject jsonObject = JSONObject.parseObject(n.toString());
            news.setTitle(jsonObject.getString("title"));
            news.setBrief(jsonObject.getString("brief"));
            news.setDate(jsonObject.getString("focus_date"));
            news.setImage(jsonObject.getString("image"));
            news.setKeyWords(jsonObject.getString("keywords"));
            news.setUrl(jsonObject.getString("url"));
            int i = newsMapper.insertNews(news);
            if (i != 1){
                return new CommonResult().error(ExcepConstants.NEWS_ERROR);
            }
        }
        return new CommonResult().success();
    }
}