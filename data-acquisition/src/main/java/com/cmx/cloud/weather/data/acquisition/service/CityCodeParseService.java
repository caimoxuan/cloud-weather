package com.cmx.cloud.weather.data.acquisition.service;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.cmx.cloud.weather.data.acquisition.config.AmapConfig;
import com.cmx.cloud.weather.data.acquisition.exception.DataAcquisitionException;
import com.cmx.cloud.weather.data.acquisition.manager.HttpConnectionManager;
import com.cmx.cloud.weather.data.acquisition.poi.listener.ExcelListener;
import com.cmx.cloud.weather.data.acquisition.poi.model.CityCode;
import com.cmx.cloud.weather.data.acquisition.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cmx
 * @Date 2019/5/26 14:27
 */
@Slf4j
@Service
public class CityCodeParseService {

    @Autowired
    private HttpConnectionManager httpConnectionManager;
    @Autowired
    private AmapConfig amapConfig;


    public List<CityCode> getCityCode() throws Exception {

        List<CityCode> cityCodes = new ArrayList<>();
        byte[] resource = httpConnectionManager.doGetFile(amapConfig.getCityCodeDownUrl());
        InputStream inputStream = new ByteArrayInputStream(resource);
        InputStream excelStream = ZipUtil.unZip(inputStream, null, Charset.defaultCharset());
        if (excelStream == null) {
            throw DataAcquisitionException.RESOURCE_DOWNLOAD_FAIL;
        }
        parseCityCodeExcel(new BufferedInputStream(excelStream), cityCodes);
        log.info("parse cityCode file size : {}", cityCodes.size());
        return cityCodes;
    }


    public static void parseCityCodeExcel(InputStream resource, List<CityCode> cityCodes) {

        ExcelReader excelReader = new ExcelReader(resource, ExcelTypeEnum.XLSX, new ExcelListener(cityCodes));

        excelReader.read(new Sheet(1, 1, CityCode.class));

    }


}
