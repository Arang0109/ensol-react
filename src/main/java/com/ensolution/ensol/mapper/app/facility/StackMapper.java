package com.ensolution.ensol.mapper.app.facility;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.request.StackUpdateRequestDto;
import com.ensolution.ensol.entity.app.facility.Stack;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(componentModel = "spring", uses = {WorkplaceMapper.class, StackInformationMapper.class})
public interface StackMapper {
  @Mapping(source = "workplace.workplaceId", target = "workplaceId")
  @Mapping(source = "stackInformation", target = "stackInformation", qualifiedByName = "toDto")
  StackDto toDto(Stack stack);
  @Mapping(source = "workplaceId", target = "workplace.workplaceId")
  @Mapping(source = "stackInformation", target = "stackInformation", qualifiedByName = "toEntity")
  Stack toEntity(StackDto stackDto);
  List<StackDto> toDtoList(List<Stack> stacks);
  
  @BeanMapping(nullValuePropertyMappingStrategy = SET_TO_NULL)
  @Mapping(target = "stackId", ignore = true)
  void updateFromDto(StackUpdateRequestDto dto, @MappingTarget Stack entity);
}