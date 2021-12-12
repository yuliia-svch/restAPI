package com.restful.company.interfaces.facade.assembler;

import com.restful.company.domain.employee.Employee;
import com.restful.company.interfaces.facade.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDTOAssembler {

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setLastname(employee.getLastname());
        employeeDTO.setPositionName(employee.getPosition().getName());
        employeeDTO.setDescription(employee.getPosition().getDescription());
        employeeDTO.setCity(employee.getAddress().getCity());
        employeeDTO.setStreet(employee.getAddress().getStreet());
        employeeDTO.setBuilding(employee.getAddress().getBuilding());
        employeeDTO.setApartment(employee.getAddress().getApartment());
        employeeDTO.setTeamId(employee.getTeam().getId());
        return employeeDTO;
    }
}
