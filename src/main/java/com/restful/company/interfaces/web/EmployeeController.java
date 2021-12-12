package com.restful.company.interfaces.web;

import com.restful.company.application.EmployeeService;
import com.restful.company.domain.employee.Employee;
import com.restful.company.interfaces.facade.assembler.EmployeeDTOAssembler;
import com.restful.company.interfaces.facade.assembler.TaskDTOAssembler;
import com.restful.company.interfaces.facade.dto.EmployeeDTO;
import com.restful.company.interfaces.facade.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDTOAssembler employeeDTOAssembler;
    private final TaskDTOAssembler taskDTOAssembler;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService
                .getAll()
                .stream()
                .map(employeeDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(employee.isPresent())
            return employeeDTOAssembler.toDTO(employee.get());
        throw new IllegalArgumentException("No employee with such id");
    }

    @GetMapping("/lastname/{lastname}")
    public EmployeeDTO getEmployeeByLastname(@PathVariable String lastname) {
        Optional<Employee> employee = employeeService.getEmployeeByLastname(lastname);
        if(employee.isPresent())
            return employeeDTOAssembler.toDTO(employee.get());
        throw new IllegalArgumentException("No employee with such lastname");
    }

    @GetMapping("/team/{id}")
    public List<EmployeeDTO> getEmployeesByTeam(@PathVariable Long id) {
        return employeeService.getEmployeesByTeam(id)
                .stream()
                .map(employeeDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/tasks/{id}")
    public List<TaskDTO> getTasksByEmployee(@PathVariable Long id) {
        return employeeService.getTasksByEmployeeId(id)
                .stream()
                .map(taskDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/tasks/assign/{employeeId}/{taskId}")
    public List<TaskDTO> assignTasksToEmployee(@PathVariable Long employeeId, @PathVariable Long taskId) {
        return employeeService.assignTaskToEmployee(employeeId, taskId)
                .stream()
                .map(taskDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/tasks/unassign/{employeeId}/{taskId}")
    public List<TaskDTO> unassignTasksFromEmployee(@PathVariable Long employeeId, @PathVariable Long taskId) {
        return employeeService.unassignTaskFromEmployee(employeeId, taskId)
                .stream()
                .map(taskDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeDTO hireEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeService.hireEmployee(employeeDTO);
        return employeeDTOAssembler.toDTO(newEmployee);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return employeeDTOAssembler.toDTO(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public EmployeeDTO fireEmployee(@PathVariable Long id) {
        Employee deletedEmployee = employeeService.fireEmployee(id);
        return employeeDTOAssembler.toDTO(deletedEmployee);
    }
}
