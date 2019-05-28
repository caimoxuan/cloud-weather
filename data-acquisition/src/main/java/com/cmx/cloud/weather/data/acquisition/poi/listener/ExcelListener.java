package com.cmx.cloud.weather.data.acquisition.poi.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cmx.cloud.weather.data.acquisition.poi.model.CityCode;

import java.util.List;

/**
 * @author cmx
 * @Date 2019/5/27 23:47
 */
public class ExcelListener extends AnalysisEventListener<CityCode> {


    private List<CityCode> cityCodes;

    public ExcelListener(List<CityCode> cityCodes){
        this.cityCodes = cityCodes;
    }

    /**
     *  解析一行调用一次
     */
    @Override
    public void invoke(CityCode cityCode, AnalysisContext analysisContext) {
        cityCodes.add(cityCode);
    }

    /**
     *  结束解析调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
