package ${beanPackage};

import java.io.Serializable;


public class ${className} implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	#foreach($item in $!{columnDatas})
	
 /**
	* ${item.columnComment}
	*/
	private ${item.fieldDataType} ${item.fieldName};	

	#end	
	
	
	#foreach($item in $!{columnDatas})
public void set${item.fieldNameForMethod}(${item.fieldDataType} ${item.fieldName}) {
		this.${item.fieldName} = ${item.fieldName};
	}

	public ${item.fieldDataType} get${item.fieldNameForMethod}() {
		return this.${item.fieldName};
	}
	
	#end
}
