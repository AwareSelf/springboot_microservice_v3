package vw.nama.web.MVCdemo.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vw.nama.web.MVCdemo.dao.EmpDao;
import vw.nama.web.MVCdemo.exception.EmployeeNotFoundException;
import vw.nama.web.MVCdemo.model.Employee;

@RequestMapping("/emprest")
@RestController
public class EmpRestController {
	
	@Autowired
	EmpDao empdao;
	
	
	@GetMapping("/emp/{empid}")
	public Employee getEmployee(@PathVariable int empid)
	{
		return this.empdao.fetchEmp(empid);
	}

	@GetMapping("/emps")
	public List<Employee> getAllEmployees()
	{
		return this.empdao.getAllEmps();
	}
	
	@PostMapping("/emp")
	public Employee addEmployee(@Valid @RequestBody Employee e)
	{
	    return this.empdao.saveEmp(e);	
	}
	
	@GetMapping("/emp/annsal/{empid}")
	public String fetchAnnSal(@PathVariable int empid,ModelMap map)
	{
		try
		{
	     Employee e = this.empdao.fetchEmp(empid);
	     double annsal = e.computeAnnSal();
	      return "annsal of emp with id:"+empid+" is:"+annsal;
		}
		catch(EmployeeNotFoundException e)
		{
			String s = "empexception:"+	e.getMessage()+", so annsal of emp can't be computed!";
			return s;
		}
	}

	@GetMapping("/emp/annsal/{empid}/{bonus}")
	public String fetchAnnSal(@PathVariable int empid,@PathVariable double bonus,ModelMap map)
	{
		try
		{
	     Employee e = this.empdao.fetchEmp(empid);
	     double annsal = e.computeAnnSal(bonus);
	      return "annsal of emp with id:"+empid+" is:"+annsal;
		}
		catch(EmployeeNotFoundException e)
		{
			String s = "empexception:"+	e.getMessage()+", so annsal of emp can't be computed!";
			return s;
		}
	   
	}

}
