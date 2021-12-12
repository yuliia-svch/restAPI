package com.restful.company.interfaces.facade.assembler;

import com.restful.company.domain.team.Team;
import com.restful.company.interfaces.facade.dto.TeamDTO;
import org.springframework.stereotype.Component;

@Component
public class TeamDTOAssembler {

    public TeamDTO toDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName(team.getName());
        return teamDTO;
    }

    public Team fromDTO(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        return team;
    }
}
