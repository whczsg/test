package com.itheima.bigevent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.bigevent.mapper.ArticleMapper;
import com.itheima.bigevent.pojo.Article;
import com.itheima.bigevent.pojo.PageBean;
import com.itheima.bigevent.service.ArticleService;
import com.itheima.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/*
文章服务子类
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    //新增文章
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId =(Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    /*
    条件分页列表查询
    pageNum 当前页码/ pageSize 每页条数/ categoryId 文章分类id/  state 发布状态
     */
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //1.创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        //2.开启分页查询 mybatis 插件 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //3.调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId,categoryId,state);

        /*
        强转是为了调用Page中提供的方法
        可以获取PageHelper分页查询后吗，得到的总记录条数和当前页数据
         */
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    //获取文章详情
    @Override
    public Article get(String id) {
        Article b = articleMapper.get(id);
        return b;
    }

    //更新文章
    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    //删除文章
    @Override
    public void delete(String id) {
        articleMapper.delete(id);
    }
}
