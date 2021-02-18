package com.mutong.mtcommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Integer id;
    private Integer tag;
    private String title;
    private String date;
    private String keyWords;
    private String image;
    private String brief;
    private String url;
}
