package com.restful.company.interfaces.web;

import com.restful.company.application.TaskService;
import com.restful.company.interfaces.facade.assembler.EmployeeDTOAssembler;
import com.restful.company.interfaces.facade.assembler.TaskDTOAssembler;
import com.restful.company.interfaces.facade.dto.EmployeeDTO;
import com.restful.company.interfaces.facade.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskDTOAssembler taskDTOAssembler;
    private final EmployeeDTOAssembler employeeDTOAssembler;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAll()
                .stream()
                .map(taskDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/{id}")
    public List<EmployeeDTO> getEmployeesByTask(@PathVariable Long id) {
        return taskService.getEmployeesByTaskId(id)
                .stream()
                .map(employeeDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TaskDTO addNewTask(@RequestBody TaskDTO taskDTO) {
        return taskDTOAssembler.toDTO(taskService.createNewTask(taskDTO));
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskDTOAssembler.toDTO(taskService.updateTask(id, taskDTO));
    }

    @DeleteMapping("/{id}")
    public TaskDTO deleteTaskById(@PathVariable Long id) {
        return taskDTOAssembler.toDTO(taskService.deleteTask(id));
    }
}
