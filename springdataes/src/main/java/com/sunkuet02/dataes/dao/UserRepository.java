package com.sunkuet02.dataes.dao;

import com.sunkuet02.dataes.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by sun on 6/7/17.
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
