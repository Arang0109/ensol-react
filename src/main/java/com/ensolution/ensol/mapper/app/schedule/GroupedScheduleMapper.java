package com.ensolution.ensol.mapper.app.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.GroupedScheduleDto;
import com.ensolution.ensol.entity.app.schedule.GroupedSchedule;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupedScheduleMapper {
  @Mapping(source = "stack.stackId", target = "stackId")
  @Mapping(source = "team.teamId", target = "teamId")
  GroupedScheduleDto toDto(GroupedSchedule groupedSchedule);
  
  @InheritInverseConfiguration(name = "toDto")
  GroupedSchedule toEntity(GroupedScheduleDto groupedScheduleDto);

  default List<GroupedScheduleDto> toDtoList(List<GroupedSchedule> schedules) {
    return schedules.stream().map(this::toDto).collect(Collectors.toList());
  }
}
