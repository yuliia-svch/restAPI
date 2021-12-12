package com.restful.company.domain.employee;

import com.restful.company.domain.task.Task;
import com.restful.company.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByLastname(String lastname);

    List<Employee> findByTasks_Id(Long id);

    List<Employee> findByTeam(Team team);
}
