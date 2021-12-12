package com.restful.company.domain.task;

import com.restful.company.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployees_Id(Long id);
}
