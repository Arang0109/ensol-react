package com.ensolution.ensol.mapper.app.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.MeasurementDataDto;
import com.ensolution.ensol.entity.app.schedule.MeasurementData;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MeasurementDataMapper {
  @Named("toDto")
  MeasurementDataDto toDto(MeasurementData measurementData);
  @Named("toEntity")
  MeasurementData toEntity(MeasurementDataDto measurementDataDto);
  
  default List<MeasurementDataDto> toDtoList(List<MeasurementData> measurementData) {
    return measurementData.stream().map(this::toDto).collect(Collectors.toList());
  }
}
