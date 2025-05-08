package com.ensolution.ensol.mapper.app.organization;

import com.ensolution.ensol.dto.app.entity.oranization.TeamDto;
import com.ensolution.ensol.entity.app.organization.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
  TeamDto toDto(Team team);
  Team toEntity(TeamDto teamDto);
  List<TeamDto> toDtoList(List<Team> teams);
}
