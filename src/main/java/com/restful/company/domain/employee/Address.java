package com.restful.company.domain.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    private int building;

    private int apartment;

    @OneToOne(mappedBy = "address")
    private Employee employee;

    public Address(){}
}
