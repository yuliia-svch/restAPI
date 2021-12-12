package com.restful.company.application;

import com.restful.company.domain.employee.Employee;
import com.restful.company.domain.team.Team;
import com.restful.company.interfaces.facade.dto.TeamDTO;

import java.util.List;

public interface TeamService {

    List<Team> getAll();

    Team createNewTeam(TeamDTO teamDTO);

    Team deleteTeam(Long id);
}
