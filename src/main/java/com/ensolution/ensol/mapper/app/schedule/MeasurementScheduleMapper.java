package com.ensolution.ensol.mapper.app.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.MeasurementScheduleDto;
import com.ensolution.ensol.entity.app.schedule.MeasurementSchedule;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MeasurementScheduleMapper {
  @Mapping(source = "scheduledStack.scheduledStackId", target = "scheduledStackId")
  @Mapping(source = "stackMeasurement.stackMeasurementId", target = "stackMeasurementId")
  MeasurementScheduleDto toDto(MeasurementSchedule measurementSchedule);
  
  @InheritInverseConfiguration(name = "toDto")
  MeasurementSchedule toEntity(MeasurementScheduleDto measurementScheduleDto);
  
  default List<MeasurementScheduleDto> toDtoList(List<MeasurementSchedule> measurementSchedules) {
    return measurementSchedules.stream().map(this::toDto).collect(Collectors.toList());
  }
  
  default List<MeasurementSchedule> toEntityList(List<MeasurementScheduleDto> scheduledMeasurements) {
    return scheduledMeasurements.stream().map(this::toEntity).collect(Collectors.toList());
  }
}