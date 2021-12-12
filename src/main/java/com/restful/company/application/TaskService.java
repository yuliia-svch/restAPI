package com.restful.company.application;

import com.restful.company.domain.employee.Employee;
import com.restful.company.domain.task.Task;
import com.restful.company.interfaces.facade.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<Task> getAll();

    List<Employee> getEmployeesByTaskId(Long id);

    Task createNewTask(TaskDTO taskDTO);

    Task updateTask(Long id, TaskDTO taskDTO);

    Task deleteTask(Long id);
}
