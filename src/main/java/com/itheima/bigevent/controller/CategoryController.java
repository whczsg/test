package com.itheima.bigevent.controller;

import com.itheima.bigevent.pojo.Category;
import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    利用validation完成参数校验，在service层为category其属性赋值
    @Validated(Category.Add.class)分组校验
 */

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /*
    新增文章分类
    参数校验并指定分组
     */
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    /*
    文章分类列表
     */
    @GetMapping
    public Result<List<Category>> list(){
       List<Category> cs = categoryService.list();
       return Result.success(cs);
    }

    /*
    获取文章分类详情
     */
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
       Category c = categoryService.findById(id);
       return Result.success(c);
    }

    /*
    更新文章分类
    参数校验并指定分组
     */
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    /*
    删除文章分类
     */
    @DeleteMapping
    public Result delete(String id){
        categoryService.delete(id);
        return Result.success();
    }
}
