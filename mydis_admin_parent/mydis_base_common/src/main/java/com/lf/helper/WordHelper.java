package com.lf.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
* <p>Title: WordHelper.java<／p>
* <p>Description: word文档操作帮助类<／p>
* @author Liangfeng
* @date 2016-12-24
* @version 1.0
 */
public class WordHelper {
	
	/**
	 * 私有化构造函数
	 */
	private WordHelper(){}
	
	/**
	 * 创建word文档
	 * @param templateDir 模板所在目录对象
	 * @param templateName 模板名称
	 * @param paramMap 参数集合
	 * @param os 输出流
	 * @throws Exception
	 */
	public static void createDocForTemplate(File templateDir,String templateName,Map<String,Object> paramMap,OutputStream os) throws Exception{
		//1.创建一个合适的configuration对象
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
		//2.设置模板所在目录
		cfg.setDirectoryForTemplateLoading(templateDir);
		cfg.setDefaultEncoding("UTF-8");
		//4.设置模板异常
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		//5.获取或创建一个模板
		Template template = cfg.getTemplate(templateName);
		//6.写到对应的输出流
		Writer writer  = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
		template.process(paramMap, writer);
		writer.flush();
		writer.close();
	}


}
