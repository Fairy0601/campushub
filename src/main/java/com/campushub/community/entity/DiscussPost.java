package com.campushub.community.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * ClassName: DiscussPost
 * Package: com.campushub.community.entity
 * Description:
 *
 * @Author 欣欣欣
 * @Create 2025/2/13 11:38
 * @Version 1.0
 */
@Data
//声明在ES中的索引名、类型（占位符、固定的）、分⽚数量、副本数量
@Document(indexName = "discusspost", type = "_doc", shards = 6, replicas = 3)  //es使用，将实体信息自动映射到es索引里
public class DiscussPost {

    //id 字段加上 @Id 注解，在 ES 中也是 id 字段；其它字段都加上 @Field 注解
    @Id
    private int id;

    //对于普通类型（Integer、Double）以及⽇期类型，type = FieldType.xxx（各⾃的类型）
    @Field(type = FieldType.Integer)
    private int userId;

    //字符串类型，type=FieldType,Text。这里的标题和内容，也是要进行分片、搜索的字段，
    //analyzer是存储时的解析器。在保存的时候要尽可能拆分出更多的关键词，增加搜索的范围，所以用"ik_max_word";
    //而在进行搜索的时候没必要拆分的那么细，拆分的聪明一点、符合预期就行了，
    //所以searchAnayzer即搜索时的解析器采用"ik_smart"
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    //analyzer:存储时的解析器，在保存的时候尽可能拆分出更多的关键词，增加搜索范围
    //searchAnalyzer:搜索时的解析器，在进行搜索的时候没必要拆分很细，拆分的灵活一点，聪明一点，符合预期就好了
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Integer)
    private int type;

    @Field(type = FieldType.Integer)
    private int status;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Integer)
    private int commentCount;

    @Field(type = FieldType.Double)
    private double score;
}
