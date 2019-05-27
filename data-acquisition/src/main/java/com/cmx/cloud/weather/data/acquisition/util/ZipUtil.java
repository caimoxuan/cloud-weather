package com.cmx.cloud.weather.data.acquisition.util;


import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author cmx
 * @Date 2019/5/26 16:19
 */
public class ZipUtil {


    private final static Charset CHARSET = Charset.forName("UTF-8");

    /**
     * 解压
     * @param inputStream 当前压缩包的字节流
     * @param fileName 要获取压缩包中的文件名称
     * @param charset 字符编码
     * @return
     * @throws IOException
     */
    public static InputStream unZip(InputStream inputStream, String fileName, Charset charset) throws IOException{
        ZipInputStream zipInputStream = new ZipInputStream(inputStream, charset);
        ZipEntry zipEntry;
        while (null != (zipEntry = zipInputStream.getNextEntry())) {

            if(fileName == null){
                return zipInputStream;
            }

            String name = zipEntry.getName();
            if (name.contains(fileName)) {
                return zipInputStream;
            }
        }
        return null;
    }


    /**
     * 压缩多个文件
     * @param map
     * @return
     * @throws IOException
     */
    public static InputStream zipOutputStreams(Map<String, ByteArrayOutputStream> map) throws IOException {
        ByteArrayOutputStream zipBuffer = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(zipBuffer, CHARSET);
        byte[] buff = new byte[1024];
        try {
            for (Map.Entry<String, ByteArrayOutputStream> entry : map.entrySet()) {
                zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                ByteArrayInputStream byteArrayInputStream = null;
                try {
                    byteArrayInputStream = new ByteArrayInputStream(map.get(entry.getKey()).toByteArray());
                    int size;
                    while ((size = byteArrayInputStream.read(buff, 0, buff.length)) != -1) {
                        zipOutputStream.write(buff, 0, size);
                    }
                } finally {
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                }
            }
        } finally {
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            zipBuffer.close();
        }
        return new ByteArrayInputStream(zipBuffer.toByteArray());
    }

}
