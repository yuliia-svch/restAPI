package com.restful.company.domain.employee;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.restful.company.domain.task.Task;
import com.restful.company.domain.team.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @ManyToMany
    @JoinTable(
            name = "employee_task",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}
