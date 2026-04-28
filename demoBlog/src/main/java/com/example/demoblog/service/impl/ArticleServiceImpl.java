package com.example.demoblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demoblog.entity.Article;
import com.example.demoblog.mapper.ArticleMapper;
import com.example.demoblog.service.IArticleService;
import org.springframework.stereotype.Service;

@Service  // 标记为Service层组件，交给Spring管理
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    // 默认实现已足够，暂无需添加额外代码
}
