package com.itheima.bigevent.service;

import com.itheima.bigevent.pojo.Category;

import java.util.List;

/*
文章分类服务类
 */
public interface CategoryService {
    //新增分类
    void add(Category category);

    //列表查询
    List<Category> list();

    //根据id查询分类信息
    Category findById(Integer id);

    //更新分类
    void update(Category category);

    //删除分类
    void delete(String id);
}
