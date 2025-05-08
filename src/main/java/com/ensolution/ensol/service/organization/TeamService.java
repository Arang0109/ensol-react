package com.ensolution.ensol.service.organization;

import com.ensolution.ensol.dto.app.entity.oranization.TeamDto;

import java.util.List;

public interface TeamService {
  List<TeamDto> findAllTeams();
}
