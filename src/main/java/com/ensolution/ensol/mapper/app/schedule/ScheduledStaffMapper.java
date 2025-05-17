package com.ensolution.ensol.mapper.app.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.ScheduledStaffDto;
import com.ensolution.ensol.entity.app.schedule.ScheduledStaff;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduledStaffMapper {
  @Mapping(source = "scheduledWorkplace.scheduledWorkplaceId", target = "scheduledWorkplaceId")
  @Mapping(source = "staff.staffId", target = "staffId")
  ScheduledStaffDto toDto(ScheduledStaff scheduledStaff);
  
  @InheritInverseConfiguration(name = "toDto")
  ScheduledStaff toEntity(ScheduledStaffDto scheduledStaffDto);
}
