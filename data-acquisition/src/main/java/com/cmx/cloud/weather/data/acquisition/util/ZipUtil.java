package com.cmx.cloud.weather.data.acquisition.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author cmx
 * @Date 2019/5/26 16:19
 */
public class ZipUtil {


    public static void unZip(InputStream stream) throws Exception{
        ZipInputStream zipInputStream = new ZipInputStream(stream);
        ZipEntry nextEntry = zipInputStream.getNextEntry();

    }

    public static InputStream getZipInputStram(InputStream stream, String fileName) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(stream);
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        return null;

    }



    public static void main(String[] args) throws Exception{
        File f = new File("C:\\Users\\Administrator\\Desktop\\Desktop.zip");
        InputStream inputStream = new FileInputStream(f);

        unZip(inputStream);
    }

}
