package ${servicePackage};

import java.util.List;

import com.zy.report.common.exception.BaseException;
import com.zy.report.common.util.Pager;
import ${entityPath}.${className};

import java.util.LinkedList;


public interface I${className}Service {

	public Class getModelClass()  throws BaseException;

	public void save(${className} po)   throws BaseException ;

	public void update(${className} po) throws BaseException;
	
	public void delete(${className} po)  throws BaseException;

	public ${className} findById(Integer id)  throws BaseException;

	public Pager find(int pageNo, int pageSize,${className} po)  throws BaseException;
	
	public List<${className}> find(${className} po)  throws BaseException;
	
	public int count(${className} po) throws BaseException;
	
}
