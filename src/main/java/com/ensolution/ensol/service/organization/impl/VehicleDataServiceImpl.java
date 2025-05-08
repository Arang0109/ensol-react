package com.ensolution.ensol.service.organization.impl;

import com.ensolution.ensol.dto.app.entity.oranization.VehicleDto;
import com.ensolution.ensol.entity.app.organization.Vehicle;
import com.ensolution.ensol.mapper.app.organization.VehicleMapper;
import com.ensolution.ensol.repository.app.jpa.organization.VehicleRepository;
import com.ensolution.ensol.service.organization.VehicleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleDataServiceImpl implements VehicleDataService {
  private final VehicleRepository vehicleRepository;
  private final VehicleMapper vehicleMapper;
  
  @Override
  public List<VehicleDto> findAll() {
    List<Vehicle> vehicles = vehicleRepository.findAll();
    return vehicleMapper.toDtoList(vehicles);
  }
}
