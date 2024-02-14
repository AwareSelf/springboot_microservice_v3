package vw.nama.web.MVCdemo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vw.nama.web.MVCdemo.model.Employee;
import vw.nama.web.MVCdemo.repo.EmpJpaRepo;

@SpringBootApplication
public class MvCdemoApplication implements CommandLineRunner {

	@Autowired
	EmpJpaRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(MvCdemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		//(String empName, LocalDate empJoiningDate, double empSalary
		Employee e = new Employee("Ram",LocalDate.of(2024,02,11),100000);
		
		repo.save(e);
		
	    e = new Employee("Radha",LocalDate.of(2024,02,12),200000);
	    repo.save(e);
	   
	}

}
