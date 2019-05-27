package com.cmx.cloud.weather.data.acquisition.exception;

/**
 * @author cmx
 * @Date 2019/5/27 23:32
 */
public class DataAcquisitionException extends RuntimeException {

    DataAcquisitionException(String message){
        super(message);
    }

    public static final DataAcquisitionException RESOURCE_DOWNLOAD_FAIL = new DataAcquisitionException("资源下载失败");


}
