package com.restful.company.domain.team;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.restful.company.domain.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Team")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees;

    public void addEmployeeToTeam(Employee employee) {
        employees.add(employee);
    }

    public void deleteEmployeeFromTeam(Employee employee) {
        employees.remove(employee);
    }
}
