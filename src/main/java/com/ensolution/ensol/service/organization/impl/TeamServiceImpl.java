package com.ensolution.ensol.service.organization.impl;

import com.ensolution.ensol.dto.app.entity.oranization.TeamDto;
import com.ensolution.ensol.service.organization.TeamDataService;
import com.ensolution.ensol.service.organization.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
  private final TeamDataService teamDataService;
  
  @Override
  public List<TeamDto> findAllTeams() {
    return teamDataService.findAll();
  }
}
