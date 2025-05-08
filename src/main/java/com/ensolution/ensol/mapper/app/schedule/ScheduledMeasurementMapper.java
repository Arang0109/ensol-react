package com.ensolution.ensol.mapper.app.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.ScheduledMeasurementDto;
import com.ensolution.ensol.entity.app.schedule.ScheduledMeasurement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ScheduledMeasurementMapper {
  @Mapping(source = "groupedSchedule.groupedScheduleId", target = "groupedScheduleId")
  @Mapping(source = "stackMeasurement.stackMeasurementId", target = "stackMeasurementId")
  ScheduledMeasurementDto toDto(ScheduledMeasurement scheduledMeasurement);
  
  @InheritInverseConfiguration(name = "toDto")
  ScheduledMeasurement toEntity(ScheduledMeasurementDto scheduledMeasurementDto);
  
  default List<ScheduledMeasurementDto> toDtoList(List<ScheduledMeasurement> scheduledMeasurements) {
    return scheduledMeasurements.stream().map(this::toDto).collect(Collectors.toList());
  }
  
  default List<ScheduledMeasurement> toEntityList(List<ScheduledMeasurementDto> scheduledMeasurements) {
    return scheduledMeasurements.stream().map(this::toEntity).collect(Collectors.toList());
  }
}