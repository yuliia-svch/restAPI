package com.restful.company.application.impl;

import com.restful.company.application.EmployeeService;
import com.restful.company.domain.employee.Address;
import com.restful.company.domain.employee.Employee;
import com.restful.company.domain.employee.EmployeeRepository;
import com.restful.company.domain.employee.Position;
import com.restful.company.domain.task.Task;
import com.restful.company.domain.task.TaskRepository;
import com.restful.company.domain.team.Team;
import com.restful.company.domain.team.TeamRepository;
import com.restful.company.interfaces.facade.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> getEmployeeByLastname(String lastname) {
        return employeeRepository.findByLastname(lastname);
    }

    @Override
    public List<Task> getTasksByEmployeeId(Long id) {
        return taskRepository.findByEmployees_Id(id);
    }

    @Override
    public List<Employee> getEmployeesByTeam(Long id) {
        Team team = teamRepository.getById(id);
        return employeeRepository.findByTeam(team);
    }

    @Override
    public Employee hireEmployee(EmployeeDTO employeeDTO) {
        Team team = teamRepository.findById(employeeDTO.getTeamId()).orElseThrow(() -> new EntityNotFoundException("Team not found"));
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setLastname(employeeDTO.getLastname());
        Position position = new Position();
        position.setName(employeeDTO.getPositionName());
        position.setDescription(employeeDTO.getDescription());
        employee.setPosition(position);
        Address address = new Address();
        address.setCity(employeeDTO.getCity());
        address.setStreet(employeeDTO.getStreet());
        address.setBuilding(employeeDTO.getBuilding());
        address.setApartment(employeeDTO.getApartment());
        employee.setAddress(address);
        employee.setTeam(team);
        employee.setTasks(new ArrayList<>());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Team team = teamRepository.findById(employeeDTO.getTeamId()).orElseThrow(() -> new EntityNotFoundException("Team not found"));
        Employee employee = employeeRepository.getById(id);
        employee.setName(employeeDTO.getName());
        employee.setLastname(employeeDTO.getLastname());
        Position position = new Position();
        position.setName(employeeDTO.getPositionName());
        position.setDescription(employeeDTO.getDescription());
        employee.setPosition(position);
        Address address = new Address();
        address.setCity(employeeDTO.getCity());
        address.setStreet(employeeDTO.getStreet());
        address.setBuilding(employeeDTO.getBuilding());
        address.setApartment(employeeDTO.getApartment());
        employee.setAddress(address);
        employee.setAddress(address);
        employee.setTeam(team);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee fireEmployee(Long id) {
        Employee employee = employeeRepository.getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public List<Task> assignTaskToEmployee(Long employeeId, Long taskId) {
        Employee employee = employeeRepository.getById(employeeId);
        Task task = taskRepository.getById(taskId);
        task.addEmployee(employee);
        employee.addTask(task);
        taskRepository.save(task);
        employeeRepository.save(employee);
        return taskRepository.findByEmployees_Id(employeeId);
    }

    @Override
    public List<Task> unassignTaskFromEmployee(Long employeeId, Long taskId) {
        Employee employee = employeeRepository.getById(employeeId);
        Task task = taskRepository.getById(taskId);
        task.removeEmployee(employee);
        employee.deleteTask(task);
        taskRepository.save(task);
        employeeRepository.save(employee);
        return taskRepository.findByEmployees_Id(employeeId);
    }
}
