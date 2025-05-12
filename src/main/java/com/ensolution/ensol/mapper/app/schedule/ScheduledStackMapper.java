package com.ensolution.ensol.mapper.app.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.ScheduledStackDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledWorkplaceDto;
import com.ensolution.ensol.entity.app.schedule.ScheduledStack;
import com.ensolution.ensol.entity.app.schedule.ScheduledWorkplace;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ScheduledStackMapper {
  @Mapping(source = "scheduledWorkplace.scheduledWorkplaceId", target = "scheduledWorkplaceId")
  @Mapping(source = "stack.stackId", target = "stackId")
  ScheduledStackDto toDto(ScheduledStack scheduledStack);
  
  @InheritInverseConfiguration(name = "toDto")
  ScheduledStack toEntity(ScheduledStackDto scheduledStackDto);

  default List<ScheduledStackDto> toDtoList(List<ScheduledStack> scheduledStacks) {
    return scheduledStacks.stream().map(this::toDto).collect(Collectors.toList());
  }
}
