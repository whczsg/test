package com.itheima.bigevent.controller;

import com.itheima.bigevent.pojo.Article;
import com.itheima.bigevent.pojo.PageBean;
import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.service.ArticleService;
import com.itheima.bigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
文章管理控制器类
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //新增文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    /*
    文章列表分页查询
    @RequestParam(required = false)声明参数是否必传
     */
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
       PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
       return Result.success(pb);
    }

    //获取文章详情
    @GetMapping("/detail")
    public Result<Article> get(String id){

       Article a = articleService.get(id);
       return Result.success(a);
    }

    //更新文章
    @PutMapping
    public Result update(@RequestBody @Validated(Article.update.class) Article article){

        articleService.update(article);
        return Result.success();
    }

    //删除文章
    @DeleteMapping
    public Result delete(String id){
        articleService.delete(id);
        return Result.success();
    }
}
