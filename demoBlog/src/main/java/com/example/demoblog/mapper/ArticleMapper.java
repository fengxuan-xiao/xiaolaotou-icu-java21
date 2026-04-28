package com.example.demoblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demoblog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper  // 让Spring容器管理该Bean
public interface ArticleMapper extends BaseMapper<Article> {
    // 无需编写任何代码，MyBatis-Plus会自动提供增删改查方法
}
