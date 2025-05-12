package com.ensolution.ensol.mapper.app.facility;

import com.ensolution.ensol.dto.app.entity.facility.StackInformationDto;
import com.ensolution.ensol.entity.app.facility.StackInformation;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(componentModel = "spring")
public interface StackInformationMapper {
  @Named("toDto")
  StackInformationDto toDto(StackInformation stackInformation);
  @Named("toEntity")
  StackInformation toEntity(StackInformationDto stackInformationDto);
  
  @BeanMapping(nullValuePropertyMappingStrategy = SET_TO_NULL)
  @Mapping(target = "stackId", ignore = true)
  void updateFromDto(StackInformationDto dto, @MappingTarget StackInformation entity);
}