package vw.nama.web.MVCdemo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vw.nama.web.MVCdemo.model.*;


@Repository
public interface EmpJpaRepo extends JpaRepository<Employee,Integer>{

}
