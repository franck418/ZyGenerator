package com.xiaozhao.commons.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/***
 * 公共使用的工具
 * <p>Title: CommonUtil</p>
 * <p>Discription: </p>
 * <p>Company: Bmind</p>
 * @author Franck
 * @version 1.0   2016年6月30日
 */
public class CommonUtil {
	
	
	
	public static boolean createFile(VelocityContext context,String fileName,String filePath,String tempPath) throws Exception{
		Boolean result = true;
		BufferedWriter writer =null;
		FileOutputStream fos =null;
		
		try {
			//生成javaBean文件
			File file = new File(filePath + fileName);
			if(!file.exists()){
				new File(file.getParent()).mkdirs();
			}
			
			VelocityEngine ve =  new VelocityEngine();
//			Template template  = ve.getTemplate("template/TempAtomicity.java", "UTF-8");
			Template template  = ve.getTemplate(tempPath, "UTF-8");
			fos = new FileOutputStream(file);
			
			writer = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			fos.close();
			
		} catch (Exception e) {
			throw e;
			
		}finally{
			if (writer!=null) {
				writer.close();
			}
			if (fos!=null) {
				fos.close();
			}
		}
		
		return result;
		
	}
	
	
	
}
