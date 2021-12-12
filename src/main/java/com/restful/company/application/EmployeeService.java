package com.restful.company.application;

import com.restful.company.domain.employee.Employee;
import com.restful.company.domain.task.Task;
import com.restful.company.domain.team.Team;
import com.restful.company.interfaces.facade.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAll();

    Optional<Employee> getEmployeeById(Long id);

    Optional<Employee> getEmployeeByLastname(String lastname);

    List<Task> getTasksByEmployeeId(Long id);

    List<Employee> getEmployeesByTeam(Long id);

    Employee hireEmployee(EmployeeDTO employeeDTO);

    Employee updateEmployee(Long id, EmployeeDTO employeeDTO);

    Employee fireEmployee(Long id);

    List<Task> assignTaskToEmployee(Long employeeId, Long taskId);

    List<Task> unassignTaskFromEmployee(Long employeeId, Long taskId);
}
