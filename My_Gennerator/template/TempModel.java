package ${modelPackage};

import org.apache.velocity.runtime.parser.node.GetExecutor;

import com.zy.report.common.action.BaseModel;

import ${beanPackage}.${className};

public class ${className}Model extends BaseModel {

	
	private ${className} form;
	
	public void setForm(${className} form){
		this.form = form;
	}
	
	public ${className} getForm(){
		return this.form;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("${className}Model [")
		#foreach($item in $!{columnDatas})
public void set${item.fieldNameForMethod}(${item.fieldDataType} ${item.fieldName}) {
		this.${item.fieldName} = ${item.fieldName};
	}
				.append("]");
		return builder.toString();
	}
	
}
