package com.lf.helper;

import java.io.File;

import org.jboss.spring.vfs.VFSResourceLoader;
import org.springframework.util.ResourceUtils;

/**
* <p>Title: FileHelper.java<／p>
* <p>Description: 文件操作帮助类<／p>
* @author Liangfeng
* @date 2017-2-10
* @version 1.0
 */
public class FileHelper {
	
	/**
	 * 私有化
	 */
	private FileHelper(){};
	
	/**
	 * 获取文件 支持classpath:表达式。
	 * @param filePath
	 * @return
	 */
	public static File getFile(String filePath){
		//1.定义file对象
		File file = null;
		try{
			//2.如果filePath不存在，则返回null
			if(StringHelper.isBlank(filePath)){
				return null;
			}
			//3.Spring框架提供的获取资源的API,支持classpath:表达式。
			file = ResourceUtils.getFile(filePath);
		}catch (Exception e) {
			try {
				//4.兼容jboss服务器classpath
                VFSResourceLoader loader = new VFSResourceLoader(VFSResourceLoader.class.getClassLoader());
                file = loader.getResource(filePath).getFile();
            } catch (Exception e2) {
                file = null;
            }
		}
        return file;
	}

}
