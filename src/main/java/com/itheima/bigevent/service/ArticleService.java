package com.itheima.bigevent.service;

import com.itheima.bigevent.pojo.Article;
import com.itheima.bigevent.pojo.PageBean;

/*
文章管理服务类
 */
public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
