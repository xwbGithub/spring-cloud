package com.cloud.elasticsearch.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 * @description
 */
@SuppressWarnings("All")
@Slf4j
public class FileToolUtils {
	/**
	 * 判断文件是否存在，不存在就创建
	 *
	 * @param file
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
}
