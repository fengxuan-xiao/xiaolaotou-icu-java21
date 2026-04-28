package com.example.demoblog.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data                          // Lombok注解，自动生成Getter, Setter, toString等
@TableName("article")          // 指定数据库表名
public class Article {
    @TableId(type = IdType.AUTO) // 指定主键，并设置为数据库自增
    private Long id;
    private String title;
    private String content;
}
