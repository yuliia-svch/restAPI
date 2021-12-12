package com.restful.company.interfaces.facade.assembler;

import com.restful.company.domain.task.Task;
import com.restful.company.interfaces.facade.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TaskDTOAssembler {

    public TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus().name());
        return taskDTO;
    }

    public Task fromDTO(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        return task;
    }
}
