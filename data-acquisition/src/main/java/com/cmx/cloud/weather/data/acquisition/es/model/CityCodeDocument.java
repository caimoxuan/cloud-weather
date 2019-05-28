package com.cmx.cloud.weather.data.acquisition.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author cmx
 * @date 2019/5/28
 */

@Data
@Document(indexName = "city_code")
public class CityCodeDocument {

    @Id
    private String id;

    private String cityCode;

    private String cityName;
    /**
     * 城市名称拼音
     */
    private String spellName;
    /**
     * 城市编码头
     */
    private String adCode;

}
