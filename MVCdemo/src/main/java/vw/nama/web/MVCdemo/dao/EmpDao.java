package vw.nama.web.MVCdemo.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vw.nama.web.MVCdemo.exception.EmployeeNotFoundException;
import vw.nama.web.MVCdemo.model.Employee;
import vw.nama.web.MVCdemo.repo.EmpJpaRepo;

@Service  //empDao
public class EmpDao {
	
		
	@Autowired
	EmpJpaRepo er;
	
	Logger logger = LoggerFactory.getLogger(EmpDao.class);
	
	public Employee saveEmp(Employee e)
	{
	    e = er.save(e);
	    
	    logger.debug("fetched employee:{}",e);
	    return e;
	}
	
	public Employee fetchEmp(int empid)
	{
		Optional<Employee> opemp = er.findById(empid);
		
		if(opemp.isPresent())
		{
			return opemp.get();
		}
		else
		{
			throw new EmployeeNotFoundException("employee with empid:"+empid+" not found!");
		}
	}
	
	
	public List<Employee> getAllEmps()
	{
		return er.findAll();
	}

}
