package com.elasticsearch;

import com.elasticsearch.dao.ItemDao;
import com.elasticsearch.pojo.Item;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author：ke
 * @ Date： 2019/6/18 14:10
 * @ Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppElasticsearch.class)
public class IndexTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ItemDao itemDao;

    /**
     * 创建索引
     */
    @Test
    public void testCreate() {
        //创建索引,会根据Item类的Document类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        //配置映射,会根据Item类中的id,Field类字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);
    }

    /**
     * 删除索引
     */
    @Test
    public void testDelete() {
        elasticsearchTemplate.deleteIndex("heima");
        System.out.println("删除成功.............");
    }

    /**
     * 添加索引数据
     */
    @Test
    public void insertIndex() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        itemDao.saveAll(list);
    }

    /**
     * 查询所有索引
     */
    @Test
    public void findAll() {
        Iterable<Item> items = itemDao.findAll();
        for (Item item : items) {
            System.out.println("品牌==========" + item);
        }
    }

    /**
     * 自定义方法查询索引
     */
    @Test
    public void testFindBy() {
        List<Item> byPriceBetween = itemDao.findByPriceBetween(2000d, 4000d);
        for (Item item : byPriceBetween) {
            System.out.println(item);
        }
    }
    /**
     * 过滤查询索引
     */
    @Test
    public void testQuery() {
        /**
         * 查询思路-->先用springdataelasticsearch原生查询拼装查询的条件,然后再用DAO层接口调用查询方法
         * 查询封装过后的条件
         */
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //结果过滤
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id", "title", "price"}, null));
        //添加查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米手机"));
        //排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        //分页
        queryBuilder.withPageable(PageRequest.of(0, 2));
        Page<Item> result = itemDao.search(queryBuilder.build());
        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
        for (Item item : result.getContent()) {
            System.out.println(item);
        }
    }

    /**
     * 聚合查询
     * 不要使用repository
     * 使用templateelasticsearch
     * 先使用原生NativeSearchQueryBuilder封装条件
     */
    @Test
    public void testAgg() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        String aggName = "popularBrand";
        //聚合
        queryBuilder.addAggregation(AggregationBuilders.terms(aggName).field("brand"));
        //查询并返回聚合结果
        AggregatedPage<Item> result = elasticsearchTemplate.queryForPage(queryBuilder.build(), Item.class);
        //解析聚合
        Aggregations aggs = result.getAggregations();
        //获取指定名称的聚合
       StringTerms terms = aggs.get(aggName);
        //获取桶
        List<StringTerms.Bucket> buckets = terms.getBuckets();
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println("key=" + bucket.getKeyAsString());
            System.out.println("docCount=" + bucket.getDocCount());
        }
    }
}
