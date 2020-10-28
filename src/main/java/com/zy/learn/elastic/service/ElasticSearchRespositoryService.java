package com.zy.learn.elastic.service;

import com.zy.learn.elastic.dto.Person;
import com.zy.learn.elastic.repository.PersonRespository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR zhangy
 * 2020-10-25  13:57
 */
@Service
@SuppressWarnings("all")
public class ElasticSearchRespositoryService {

    @Autowired
    private final PersonRespository personRespository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public ElasticSearchRespositoryService(PersonRespository personRespository) {
        this.personRespository = personRespository;
    }

    /**
     * 通过Respository查询数据
     * @param descirbe
     * @return
     */
    public List<Person>  getPersonListByRespository(String name, int age){
        return  personRespository.findPeopleByNameAndAge(name,age);
    }

    /**
     * 通过Respository查询数据
     * @param descirbe
     * @return
     */
    public List<Person>  getPersonListRespository(String descirbe){
        return  personRespository.findAllByDescribe(descirbe);
    }


    /**
     * 更新数据
     * @param person
     * @return
     */
    public Person updatePerson(Person person ) {
        return  personRespository.save(person);
    }

    /**
     * 分页排序：注意排序字段不能为Text类型
     * @param pageable
     * @return
     */
    public Page<Person> getPersonByPage(int page, int size, String sortStr) {
        Pageable pageable =  PageRequest.of(page, size, !StringUtils.isEmpty(sortStr) ? Sort.by(Sort.Order.asc(sortStr)) : Sort.unsorted());
        return personRespository.findAll( pageable);
    }

    /**
     * 高亮分页显示
     * @param page
     * @param size
     * @param keyWords
     * @return
     */
    public Page<Person> getPersonByPageAndKeyWorkHeighLight(int page, int size, String keyWords) {
        // 构建查询
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 组合查询，boost即为权重，数值越大，权重越大
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.multiMatchQuery(keyWords, "name").boost(3))
                .should(QueryBuilders.multiMatchQuery(keyWords, "provience").boost(2))
                .should(QueryBuilders.multiMatchQuery(keyWords, "describe").boost(1));

        // 高亮设置
        queryBuilder.withHighlightFields(
                 new HighlightBuilder.Field("name")
                ,new HighlightBuilder.Field("provience")
                ,new HighlightBuilder.Field("describe"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();

        queryBuilder.withQuery(boolQueryBuilder);
        queryBuilder.withPageable(PageRequest.of(page,size));
        SearchHits<Person> search = elasticsearchRestTemplate.search(queryBuilder.build(), Person.class);

        //得到查询返回的内容
        List<SearchHit<Person>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<Person> pageInfo = new ArrayList<>();
        //遍历返回的内容进行处理
        for(SearchHit<Person> searchHit:searchHits){
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setName(highlightFields.get("name")==null ? searchHit.getContent().getName():highlightFields.get("name").get(0));
            searchHit.getContent().setProvience(highlightFields.get("provience")==null ? searchHit.getContent().getProvience():highlightFields.get("provience").get(0));
            searchHit.getContent().setDescribe(highlightFields.get("describe")==null ? searchHit.getContent().getDescribe():highlightFields.get("describe").get(0));
            //放到实体类中
            pageInfo.add(searchHit.getContent());
        }

        return new PageImpl<>(pageInfo, PageRequest.of(page,size), search.getTotalHits());
    }
}
