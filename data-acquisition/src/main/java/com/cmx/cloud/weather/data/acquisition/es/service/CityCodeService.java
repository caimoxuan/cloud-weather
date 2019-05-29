package com.cmx.cloud.weather.data.acquisition.es.service;

import com.cmx.cloud.weather.data.acquisition.es.model.CityCodeDocument;
import com.cmx.cloud.weather.data.acquisition.es.repository.CityCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cmx
 * @date 2019/5/28
 */
@Slf4j
@Service
public class CityCodeService {

    @Autowired
    private CityCodeRepository cityCodeRepository;

    public void insert(List<CityCodeDocument> cityCodeDocuments){
        cityCodeRepository.saveAll(cityCodeDocuments);
    }


    /**
     * 匹配输入拼音或者城市名字段的document
     * @param matchCode
     * @return
     */
    public List<CityCodeDocument> matchCity(String matchCode, int startPage, int size){
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().
                should(QueryBuilders.matchQuery("cityName", matchCode)).
                should(QueryBuilders.matchQuery("spellName", matchCode));

        Pageable pageable = PageRequest.of(startPage, size == 0 ? 10 : size);

        Page<CityCodeDocument> search = cityCodeRepository.search(queryBuilder, pageable);

        List<CityCodeDocument> documentList = new ArrayList<>();

        search.forEach(documentList::add);

        return documentList;
    }

}
