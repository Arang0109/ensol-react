package com.ensolution.ensol.mapper.app.organization;

import com.ensolution.ensol.dto.app.entity.oranization.TeamDto;
import com.ensolution.ensol.dto.app.entity.oranization.VehicleDto;
import com.ensolution.ensol.entity.app.organization.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
  VehicleDto toDto(Vehicle team);
  Vehicle toEntity(VehicleDto vehicleDto);
  List<VehicleDto> toDtoList(List<Vehicle> vehicles);
}