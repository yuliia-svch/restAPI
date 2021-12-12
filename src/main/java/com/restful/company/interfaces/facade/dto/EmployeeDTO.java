package com.restful.company.interfaces.facade.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private String name;
    private String lastname;
    //address
    private String city;
    private String street;
    private int building;
    private int apartment;
    //position
    private String positionName;
    private String description;

    private Long teamId;
}
