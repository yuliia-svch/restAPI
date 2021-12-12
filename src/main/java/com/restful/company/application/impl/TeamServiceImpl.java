package com.restful.company.application.impl;

import com.restful.company.application.TeamService;
import com.restful.company.domain.employee.Employee;
import com.restful.company.domain.team.Team;
import com.restful.company.domain.team.TeamRepository;
import com.restful.company.interfaces.facade.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team createNewTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setEmployees(new ArrayList<>());
        return teamRepository.save(team);
    }

    @Override
    public Team deleteTeam(Long id) {
        Team team  = teamRepository.getById(id);
        teamRepository.delete(team);
        return team;
    }
}
