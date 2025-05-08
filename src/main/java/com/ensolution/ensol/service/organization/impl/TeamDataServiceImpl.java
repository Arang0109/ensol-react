package com.ensolution.ensol.service.organization.impl;

import com.ensolution.ensol.dto.app.entity.oranization.TeamDto;
import com.ensolution.ensol.entity.app.organization.Team;
import com.ensolution.ensol.mapper.app.organization.TeamMapper;
import com.ensolution.ensol.repository.app.jpa.organization.TeamRepository;
import com.ensolution.ensol.service.organization.TeamDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamDataServiceImpl implements TeamDataService {
  private final TeamRepository teamRepository;
  private final TeamMapper teamMapper;
  
  @Override
  public List<TeamDto> findAll() {
    List<Team> teams = teamRepository.findAll();
    return teamMapper.toDtoList(teams);
  }
}
