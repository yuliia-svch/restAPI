package com.restful.company.interfaces.web;

import com.restful.company.application.TeamService;
import com.restful.company.domain.team.Team;
import com.restful.company.interfaces.facade.assembler.TeamDTOAssembler;
import com.restful.company.interfaces.facade.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final TeamDTOAssembler teamDTOAssembler;

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getAll()
                .stream()
                .map(teamDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TeamDTO createNewTeam(@RequestBody TeamDTO teamDTO) {
        Team team = teamService.createNewTeam(teamDTO);
        return teamDTOAssembler.toDTO(team);
    }

    @DeleteMapping("/{id}")
    public TeamDTO deleteTeam(@PathVariable Long id) {
        return teamDTOAssembler.toDTO(teamService.deleteTeam(id));
    }
}
