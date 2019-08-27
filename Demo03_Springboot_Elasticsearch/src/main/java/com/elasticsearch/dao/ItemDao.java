package com.elasticsearch.dao;

import com.elasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ Author：ke
 * @ Date： 2019/6/20 14:10
 * @ Version 1.0
 */

public interface ItemDao extends ElasticsearchRepository<Item, Long> {
    /**
     * 自定义方法
     */
    List<Item> findByPriceBetween(Double begin, Double end);
}
