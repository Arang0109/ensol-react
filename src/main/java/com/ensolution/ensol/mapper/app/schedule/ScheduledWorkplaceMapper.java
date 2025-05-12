package com.ensolution.ensol.mapper.app.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.ScheduledWorkplaceDto;
import com.ensolution.ensol.entity.app.schedule.ScheduledWorkplace;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ScheduledWorkplaceMapper {
  @Mapping(source = "workplace.workplaceId", target = "workplaceId")
  @Mapping(source = "team.teamId", target = "teamId")
  ScheduledWorkplaceDto toDto(ScheduledWorkplace scheduledWorkplace);
  
  @InheritInverseConfiguration(name = "toDto")
  ScheduledWorkplace toEntity(ScheduledWorkplaceDto scheduledWorkplaceDto);

  default List<ScheduledWorkplaceDto> toDtoList(List<ScheduledWorkplace> schedules) {
    return schedules.stream().map(this::toDto).collect(Collectors.toList());
  }
}
