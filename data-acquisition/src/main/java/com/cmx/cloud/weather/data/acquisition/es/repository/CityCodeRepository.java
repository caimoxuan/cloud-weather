package com.cmx.cloud.weather.data.acquisition.es.repository;

import com.cmx.cloud.weather.data.acquisition.es.model.CityCodeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cmx
 * @date 2019/5/28
 */
@Repository
public interface CityCodeRepository  extends ElasticsearchRepository<CityCodeDocument, String>  {

}
