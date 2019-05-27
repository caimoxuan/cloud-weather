package com.cmx.cloud.weather.data.acquisition.poi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author cmx
 * @Date 2019/5/27 23:29
 */
@Data
public class CityCode extends BaseRowModel{

    @ExcelProperty(value = "中文名", index = 0)
    private String cityName;

    @ExcelProperty(value = "adcode", index = 1)
    private String adCode;

    @ExcelProperty(value = "cityCode", index = 2)
    private String cityCode;


}
