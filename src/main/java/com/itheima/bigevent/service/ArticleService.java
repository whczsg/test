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

    //获取文章详情
    Article get(String id);

    //更新文章
    void update(Article article);

    //删除文章
    void delete(String id);
}
