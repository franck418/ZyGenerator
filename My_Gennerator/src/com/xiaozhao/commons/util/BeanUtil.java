package com.xiaozhao.commons.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xiaozhao.commons.generator.ColumnEntity;


/***
 * bean生成相关的工具
 * <p>Title: BeanUtil</p>
 * <p>Discription: </p>
 * <p>Company: Bmind</p>
 * @author Franck
 * @version 1.0   2016年6月29日
 */
public class BeanUtil {

	public static String DBURL = "",
			DBUserName = "bmind",
			DBPassword = "bmind0214",
			schema = "report";
	
	/***
	 * mysql数据类型转换为java数据类型
	 * 还不完整，需要详细对照才行
	 * @author Franck
	 * @return
	 * 2016年6月29日
	 */
	public String parseDataTypeByMySql(String dataType) {
		
		dataType = dataType.toUpperCase();
		
		if(dataType.equals("CHAR") 
				||dataType.equals("VARCHAR")
				||dataType.equals("TINYTEXT")
				||dataType.equals("TEXT")
				||dataType.equals("BLOB")
				||dataType.equals("MEDIUMTEXT")
				||dataType.equals("MEDIUMBLOB")
				||dataType.equals("LONGTEXT")
				||dataType.equals("LONGBLOB")
				||dataType.equals("LONGBLOB")){
			
			return "String";
		}else if (dataType.equals("TINYINT")
				||dataType.equals("SMALLINT")
				||dataType.equals("MEDIUMINT")
				||dataType.equals("INT")) {
			return "Integer";
					
		}else if (dataType.equals("BIGINT")){
			return "Long";
		}
		
		return "String";
		

	}
	
	/***
	 * copy暂时使用的不完整的数据类型转换方法
	 * @author Franck
	 * @param type
	 * @return
	 * 2016年6月29日
	 */
	public static String getType(String type) {
		type = type.toLowerCase();
		if ("char".equals(type) || "varchar".equals(type) || "varbinary".equals(type)) {
			return "String";
		} else if ("int".equals(type)) {
			return "Integer";
		} else if ("bigint".equals(type)) {
			return "Long";
		} else if ("timestamp".equals(type) || "date".equals(type) || "datetime".equals(type) || "time".equals(type)) {
			return "java.util.Date";
		} else if ("tinyint".equals(type)) {
			return "Integer";
		} else if ("decimal".equals(type)) {
			return "BigDecimal";
		} else if ("text".equals(type)) {
			return "String";
		} else if ("double".equals(type) || "float".equals(type)) {
			return "Double";
		}
		return "String";
	}
	
	/***
	 * 暂时使用的不完整的数默认值转换方法
	 * @author Franck
	 * @param type
	 * @return
	 * 2016年6月29日
	 */
	public static String getDefaultVal(String type) {
		type = type.toLowerCase();
		if ("char".equals(type) || "varchar".equals(type) || "varbinary".equals(type) || "text".equals(type)) {
			return "\"\"";
		} else if ("int".equals(type) || "tinyint".equals(type)) {
			return "0";
		} else if ("bigint".equals(type)) {
			return "0l";
		} else if ("timestamp".equals(type) || "date".equals(type) || "datetime".equals(type) || "time".equals(type)) {
			return "null";
		} else if ("double".equals(type) || "float".equals(type)) {
			return "0.0";
		}
		return "\"\"";
	}
	
	/***
	 * copy暂时使用的不完整的数据类型转换方法
	 * @author Franck
	 * @param type
	 * @return
	 * 2016年6月29日
	 */
	public static String getDataTypeComplete(String type) {
		type = type.toLowerCase();
		if ("char".equals(type) || "varchar".equals(type) || "varbinary".equals(type) || "text".equals(type)) {
			return "java.lang.String";
		} else if ("int".equals(type) || "tinyint".equals(type)) {
			return "java.lang.Integer";
		} else if ("bigint".equals(type)) {
			return "java.lang.Long";
		} else if ("timestamp".equals(type) || "date".equals(type) || "datetime".equals(type) || "time".equals(type)) {
			return "java.util.Date";
		} else if ("double".equals(type) || "float".equals(type)) {
			return "java.lang.Double";
		}
		return "java.lang.String";
	}
	
	/***
	 * 格式化字段名，要求字段名单词之间必须用下划线隔开
	 * @author Franck
	 * @param fieldName  字段名
	 * @param subIndex	   从第几个标识开始截取,
	 * @return
	 * 2016年6月29日
	 */
	public static String formatFieldName(String fieldName,Integer subIndex){
		
		fieldName = fieldName.toLowerCase();
		StringBuilder sbFieldName = new StringBuilder();
		
		if(subIndex>0){
//			fieldName = fieldName.substring(fieldName.indexOf("_"));
		}
		
		if(fieldName.indexOf("_")>-1){
			
			String[] worlds = fieldName.split("_");
			sbFieldName.append(worlds[0]);
			
			for (int i = 1; i < worlds.length; i++) {
				sbFieldName.append( worlds[i].substring(0, 1).toUpperCase()+worlds[i].substring(1) );
			}
		}else {
			sbFieldName.append(fieldName);
		}
		
		return sbFieldName.toString();
	}
	
	
	/***
	 * 格式化字段名，要求字段名单词之间必须用下划线隔开
	 * @author Franck
	 * @param fieldName  字段名
	 * @param subIndex	   从第几个标识开始截取,
	 * @return
	 * 2016年6月29日
	 */
	public static String formatFieldNameForMethod(String fieldName,Integer subIndex){
		
		fieldName = formatFieldName(fieldName,subIndex);
		
		return fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	}
	
	
	
	
	public static List<ColumnEntity> getColumnList(String tableSchema,String tableName) throws Exception{
		
		//获取表格的列数据
		String SQLColumns = "SELECT distinct COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT,COLUMN_KEY FROM information_schema.columns WHERE table_name =  '"+tableName+"' and TABLE_SCHEMA='"+tableSchema+"' ";
		
		Connection connection = DriverManager.getConnection(DBURL, DBUserName, DBPassword);
		
		PreparedStatement ps = connection.prepareStatement(SQLColumns);
		ResultSet rs = ps.executeQuery();
		
		List<ColumnEntity> columnDatas = new ArrayList<ColumnEntity>();
		
		while (rs.next()) {
			
			String columnName = rs.getString("COLUMN_NAME");
			String columnDataType= rs.getString("DATA_TYPE");
			String columnComment= rs.getString("COLUMN_COMMENT");
			String columnKey = rs.getString("COLUMN_KEY");
			
			ColumnEntity columnData = new ColumnEntity();
			columnData.setColumnComment(columnComment);
			columnData.setColumnDataType(columnDataType);
			columnData.setColumnName(columnName);
			columnData.setColumnKey(columnKey);
			
			columnData.setFieldName(BeanUtil.formatFieldName(columnName, 0));
			columnData.setFieldComment(columnComment);
			columnData.setFieldDataType(BeanUtil.getType(columnDataType));
			columnData.setFieldNameForMethod(BeanUtil.formatFieldNameForMethod(columnName, 0));
			columnData.setFieldDataTypeCompete(BeanUtil.getDataTypeComplete(columnDataType));
			
			columnDatas.add(columnData);
		}
		
		return columnDatas;
	}
	
	
	
	
	
	
	
	
	
	
	
}
