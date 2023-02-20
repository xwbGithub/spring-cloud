package org.xwb.springcloud.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Administrator
 * @description 文件工具类
 */
@SuppressWarnings("All")
@Slf4j
public class FileToolUtils {
    /**
     * 判断文件是否存在，不存在就创建
     *
     * @param url 文件夹地址
     */
    public static void createFile(String url) {
        File file = new File(url);
        if (file.exists()) {
        } else {
            //getParentFile() 获取上级目录(包含文件名时无法直接创建目录的)
            if (!file.getParentFile().exists()) {
                //创建上级目录
                file.getParentFile().mkdirs();
            }
            try {
                //在上级目录里创建文件
                file.createNewFile();
            } catch (IOException e) {
                log.error("创建文件失败!:{}", e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //删除File对象中抽象的路径方法
    public static void deleteFile(File dir) {
        try {
            //将file封装的路径下对象转换为数组
            File[] files = dir.listFiles();
            //判断这个数组为不为空,如果不为空,就执行内部代码
            if (null != files) {
                for (File file : files) {
                    //判断是否为文件
                    if (file.isFile()) {
                        //如果为文件,执行删除
                        log.info("删除文件：{}", file.getAbsolutePath());
                        file.delete();
                    } else {
                        //如果不为文件,就(递归)进入这个文件夹,删除文件
                        deleteFile(file);
                    }
                }
                //删除全部文件后删除空文件夹,最后删除自己
                dir.delete();
            }
        } catch (Exception e) {
            log.error("delete file error :{}", e.getMessage());
        }
    }

    /**
     * 判断是否存在文件夹，不存在则新建
     *
     * @param url 文件夹路径
     */
    public static void createFolder(String url) {
        File file = new File(url);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * 判断是否存在文件，不存在则新建，若存在则覆盖
     *
     * @param url 文件夹路径
     */

    public static void createNoExistsFile(String url) {
        File file = new File(url);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            log.info("文件创建失败：{}", url);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void deleteFolder(String url) {
        try {
            File directory = new File(url);
            FileUtils.deleteDirectory(directory);
        } catch (IOException e) {
            log.info("文件夹删除失败：{}", url);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String downloadNetUrlImg(String tempFolder, String urlString) {
        try {
            String substring = urlString.substring(urlString.lastIndexOf("/") + 1);
            log.info("电子公章url:{},截取到的电子公章文件名称{}", urlString, substring);
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024 * 2];
            // 读取到的数据长度
            int len;
            File filePackage = new File(tempFolder);
            if (!filePackage.exists()) {
                log.info("需要创建的文件夹地址：{}", tempFolder);
                boolean mkdir = filePackage.mkdir();
                log.info("创建文件夹结果：{},文件夹路径：{}", mkdir, filePackage);
            }
            // 输出的文件流
            String filename = filePackage + File.separator + substring;  //下载路径及下载图片名称
            log.info("下载的文件路径：{}", filename);
            File file = new File(filename);
            FileOutputStream os = new FileOutputStream(file, true);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            log.info("保存后的文件路径：{}", filename);
            // 完毕，关闭所有链接
            os.close();
            is.close();
            return filename;
        } catch (Exception e) {
            log.error("下载网络图片异常{}", e.getMessage());
            return null;
        }
    }
}
