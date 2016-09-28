package com.xiaozhao.commons.generator;

/***
 * 列数据的实体,用于模板生成java实体类
 * <p>Title: ColumnData</p>
 * <p>Discription: </p>
 * <p>Company: Bmind</p>
 * @author Franck
 * @version 1.0   2016年6月28日
 */
public class ColumnEntity {
	
	
	private String columnName;
	private String columnDataType;
	private String columnComment;
	private String columnKey;
	
	private String fieldName;
	private String fieldNameForMethod;
	private String fieldDataType;
	private String fieldComment;
	private String fieldDataTypeCompete;
	
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnDataType() {
		return columnDataType;
	}
	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldNameForMethod() {
		return fieldNameForMethod;
	}
	public void setFieldNameForMethod(String fieldNameForMethod) {
		this.fieldNameForMethod = fieldNameForMethod;
	}
	public String getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(String fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public String getFieldComment() {
		return fieldComment;
	}
	public void setFieldComment(String fieldComment) {
		this.fieldComment = fieldComment;
	}
	public String getFieldDataTypeCompete() {
		return fieldDataTypeCompete;
	}
	public void setFieldDataTypeCompete(String fieldDataTypeCompete) {
		this.fieldDataTypeCompete = fieldDataTypeCompete;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
	
	
	
	
	
	
	
}
