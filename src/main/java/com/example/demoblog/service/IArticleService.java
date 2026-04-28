package com.example.demoblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demoblog.entity.Article;

// 继承IService<Article>，获得Service层的CRUD方法
public interface IArticleService extends IService<Article> {
    // 可以在这里定义业务方法，如分页查询等，但简单CRUD暂时不需要
}
