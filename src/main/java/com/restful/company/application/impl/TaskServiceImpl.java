package com.restful.company.application.impl;

import com.restful.company.application.TaskService;
import com.restful.company.domain.employee.Employee;
import com.restful.company.domain.employee.EmployeeRepository;
import com.restful.company.domain.task.Task;
import com.restful.company.domain.task.TaskRepository;
import com.restful.company.interfaces.facade.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByTaskId(Long id) {
        return employeeRepository.findByTasks_Id(id);
    }

    @Override
    public Task createNewTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        task.setEmployees(new ArrayList<>());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.getById(id);
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        return taskRepository.save(task);
    }

    @Override
    public Task deleteTask(Long id) {
        Task task = taskRepository.getById(id);
        taskRepository.delete(task);
        return task;
    }
}
