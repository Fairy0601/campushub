package com.campushub.community.mapper.elasticsearch;

import com.campushub.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {
    //内置了很多⽅法，可以像数据库⼀样增删改查
}
