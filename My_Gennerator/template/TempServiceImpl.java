package ${serviceImplPackage};

import java.util.List;

import com.zy.report.common.exception.BaseException;
import com.zy.report.common.util.Pager;
import ${entityPath}.${className};
import ${daoPackage}.${className}DAO;

import java.util.LinkedList;


public class ${className}ServiceImpl implements I${className}Service {
	
	private ${className}DAO ${classNameForfield}DAO;
	
	public void set${className}DAO(${className}DAO ${classNameForfield}DAO){
		this.${classNameForfield}DAO = ${classNameForfield}DAO;
	}
	
	public Class getModelClass()  throws BaseException{
		return ${classNameForfield}DAO.getModelClass();
	}

	public void save(${className} po)   throws BaseException {
		${classNameForfield}DAO.save(po);
	}

	public void update(${className} po) throws BaseException{
		${classNameForfield}DAO.update(po);
		
	}
	
	public void delete(${className} po)  throws BaseException{
		${classNameForfield}DAO.delete(po);
	}
	
	public ${className} findById(Integer id)  throws BaseException{
		return ${classNameForfield}DAO.findById(id);
	}

	public Pager find(int pageNo, int pageSize,${className} po)  throws BaseException{
		return ${classNameForfield}DAO.find( pageNo,  pageSize, po);
	}
	
	public List<${className}> find(${className} po)  throws BaseException{
		return ${classNameForfield}DAO.find(po);
	}
	
	public int count(${className} po) throws BaseException{
		return ${classNameForfield}DAO.count(po);
	}
	
}
