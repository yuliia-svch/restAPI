package com.restful.company.domain.team;

import com.restful.company.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    //List<Employee> findEmployeesById(Long id);
}
