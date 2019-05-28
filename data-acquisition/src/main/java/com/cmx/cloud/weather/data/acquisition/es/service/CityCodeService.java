package com.cmx.cloud.weather.data.acquisition.es.service;

import com.cmx.cloud.weather.data.acquisition.es.model.CityCodeDocument;
import com.cmx.cloud.weather.data.acquisition.es.repository.CityCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CityCodeDocument> matchCity(String matchCode){
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().
                should(QueryBuilders.matchQuery("cityName", matchCode)).
                should(QueryBuilders.matchQuery("spellName", matchCode));
        Iterable<CityCodeDocument> cityCodeDocuments = cityCodeRepository.search(queryBuilder);

        List<CityCodeDocument> documentList = new ArrayList<>();

        cityCodeDocuments.forEach(documentList::add);

        return documentList;
    }

}
