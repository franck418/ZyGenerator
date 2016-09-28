package com.xiaozhao.commons.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.sun.org.apache.regexp.internal.REUtil;
import com.sun.org.apache.regexp.internal.recompile;
import com.xiaozhao.commons.util.BeanUtil;
import com.xiaozhao.commons.util.CommonUtil;


public class Creater {
	
	public static void main(String[] args) {
		
		
		try {
			VelocityContext context = new VelocityContext();
			BeanUtil.DBURL = "jdbc:mysql://localhost:3306/report?useUnicode=true&characterEncoding=utf-8";
			
			
			
			String tableName = "zy_product";
			String filePath = "F:/workspace/zy/src/com/zy/report/core/";
			String modularName= "course";
//			String filePath = "F:/workspace/My_Gennerator/src/";			
			
			
			String className =getTablesNameToClassName(tableName);
			String modularNameForImport = StringUtils.isBlank(modularName)?"":"."+modularName;
			
			String beanPackage = "com.zy.report.core.entity"+modularNameForImport;
			String entityPath =  "com.zy.report.core.entity"+modularNameForImport;
			String daoPackage = "com.zy.report.core.dao"+modularNameForImport;
			String servicePackage = "com.zy.report.core.service"+modularNameForImport;
			String serviceImplPackage = "com.zy.report.core.service"+modularNameForImport+".impl";
			String actionPackage = "";
			
			
			List<ColumnEntity> columnDatas = BeanUtil.getColumnList("report", tableName);
			context.put("columnDatas", columnDatas);
			context.put("className", className);
			context.put("tableName", tableName);
			context.put("classNameForfield", className.substring(0, 1).toLowerCase()+className.substring(1));
			context.put("beanPackage", beanPackage);
			context.put("entityPath", entityPath);
			context.put("daoPackage", daoPackage);
			context.put("servicePackage", servicePackage);
			context.put("serviceImplPackage", serviceImplPackage);
			context.put("actionPackage", actionPackage);
			
			
			
			CommonUtil.createFile(context, className+".java", filePath+"/entity/"+modularName+"/", "template/TempAtomicity.java");
			CommonUtil.createFile(context, className+".hbm.xml", filePath+"/entity/"+modularName+"/", "template/TempBeanHbm.xml");
			CommonUtil.createFile(context, className+"DAO.java", filePath+"dao/"+modularName+"/", "template/TempDAO.java");
			CommonUtil.createFile(context, "I"+className+"Service.java", filePath+"/service/"+modularName+"/", "template/TempService.java");
			CommonUtil.createFile(context, className+"ServiceImpl.java", filePath+"/service/"+modularName+"/impl/", "template/TempServiceImpl.java");
			CommonUtil.createFile(context, "spring.xml", filePath+"/spring/", "template/TempSpringContext.xml");
			
			
			
			System.out.println("生成成功......");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
	

	
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>表名转换成类名，去掉tb||t， 每_首字母大写<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2011-12-21 <br>
	 * 
	 * @param tableName
	 * @return
	 */
	public static String getTablesNameToClassName(String tableName) {
		String[] split = tableName.split("_");
		if (split.length > 1) {
			int index = 0;
			if ("tb".equals(split[0].toLowerCase()) || "t".equals(split[0].toLowerCase())) {
				index = 2;
			}
			StringBuffer sb = new StringBuffer();
			for (int i = index; i < split.length; i++) {
				String sp = split[i].substring(0, 1).toUpperCase()
						+ split[i].substring(1, split[i].length());
				sb.append(sp);
			}
			System.out.println(sb.toString());
			return sb.toString();
		} else {
			String temp = split[0].substring(0, 1).toUpperCase() + split[0].substring(1, split[0].length());
			return temp;
		}
	}
	
	
	
	
	
	
	
	
	
}
