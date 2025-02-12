package com.itheima.bigevent.mapper;

import com.itheima.bigevent.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("""
            insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)
            values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})
            """)
    void add(Article article);


    //条件分页，动态sql,映射配置文件更方便
    List<Article> list(Integer userId, Integer categoryId, String state);

    //根据id获取文章详情
    @Select("""
            select * from article
            where id=#{id}
            """)
    Article get(String id);

    //更新文章
    @Update("""         
            update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=now()
            where id=#{id}
            """)
    void update(Article article);

    //删除文章
    @Delete("""
            delete from article where id=#{id}
            """)
    void delete(String id);
}
