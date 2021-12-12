package com.restful.company.domain.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Position")
@Setter
@Getter
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne(mappedBy = "position")
    private Employee employee;

    public Position(){}
}
