package com.example.demoblog.controller;


import com.example.demoblog.common.Result;
import com.example.demoblog.entity.Article;
import com.example.demoblog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // 标记为控制器，所有方法返回JSON数据
@RequestMapping("/api")  // 统一接口前缀
@CrossOrigin(origins = "http://localhost:5173")  // 允许前端跨域请求，端口为Vue默认开发端口
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    // 查询所有文章 (GET)
    @GetMapping("/articles")
    //Result<String>
    public Result<List<Article>> getAllArticles() {
        List<Article> list = articleService.list();
        //return list;  // 调用MyBatis-Plus的list()方法查询所有数据
        return Result.success(list);
    }

    // 新增文章 (POST)
    @PostMapping("/articles")
    public Result<Article> addArticle(@RequestBody Article article) {
        articleService.save(article);  // 调用MyBatis-Plus的save()方法保存数据
        return Result.success(article);              // 返回新增的对象（包含后端生成的id）
    }

    // 删除文章 (DELETE)
    @DeleteMapping("/articles/{id}")
    public Result<String> deleteArticle(@PathVariable Long id) {
        articleService.removeById(id); // 调用MyBatis-Plus的removeById()方法删除数据
        return Result.success("删除成功");
    }
}