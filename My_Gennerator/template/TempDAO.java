package ${daoPackage};

import java.util.List;

import com.zy.report.common.hibernate.BaseDAOHibernate;
import com.zy.report.common.util.Pager;
import com.zy.report.core.entity.questions.QuestionsLibConstants;

import java.util.LinkedList;

import ${entityPath}.${className};


public class ${className}DAO extends BaseDAOHibernate {

	public Class getModelClass() {
		return ${className}.class;
	}

	public void save(${className} po) {
		this.createObject(po);
	}

	public void update(${className} po) {
		this.updateObject(po);
	}

	public void delete(${className} po) {
		this.deleteObject(po);
	}

	public ${className} findById(Integer id) {
		return (${className}) findObjectById(id);
	}

	@SuppressWarnings("unchecked")
	public Pager find(int pageNo, int pageSize,${className} po){
		Pager pager = new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		
		String totalSQL ="select count(id) from ${className} where id>0 %s ";
		String dataSQL = "from ${className} where id>0 %s order by id desc ";
		String condition ="";
		List<Object> values = new LinkedList<Object>();
		
#foreach($item in $!{columnDatas})
	#set($testStr = "po.get"+$!item.fieldNameForMethod + "() != null")
	#set($conditionStr = " condition += "" and " + $item.fieldName+"=? "" ")
	#set($valueStr = "po.get"+$item.fieldNameForMethod+"()" )

	#if($!item.fieldDataType == 'String')
		#set($testStr = $!testStr + " && !po.get" + $!item.fieldNameForMethod + "().equals("""")")
		#set($conditionStr = "  condition += "" and  " + $item.fieldName+" like ? "" " )
		#set($valueStr =" ""%"" + "+"po.get"+$item.fieldNameForMethod+"()+  ""%"" " )
 	#end
		
 	if($testStr){
 		$conditionStr;
		values.add($valueStr);
 	}
	
#end
	
		totalSQL = String.format(totalSQL, condition);
		dataSQL = String.format(dataSQL, condition);
		
		Long total = (Long)getList(totalSQL, values).get(0);
        pager.setTotal(total);
		
        List<${className}> result = getListForPage(dataSQL, values, (pageNo - 1) * pageSize, pageSize);
        pager.setResultList(result);
		return pager;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<${className}> find(${className} po) {
		Pager pager  = find(1,1000000,po);
		return pager.getResultList();
	}

	public int count(${className} po) {
		Pager pager  = find(1,1,po);
		return pager.getTotal();
	}


	
}
