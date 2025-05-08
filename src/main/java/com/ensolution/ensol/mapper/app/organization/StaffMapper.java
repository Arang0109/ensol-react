package com.ensolution.ensol.mapper.app.organization;

import com.ensolution.ensol.dto.app.entity.oranization.StaffDto;
import com.ensolution.ensol.entity.app.organization.Staff;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
  StaffDto toDto(Staff staff);
  Staff toEntity(StaffDto staffDto);
  List<StaffDto> toDtoList(List<Staff> staffs);
}
