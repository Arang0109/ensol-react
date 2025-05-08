package com.ensolution.ensol.service.organization.impl;

import com.ensolution.ensol.dto.app.entity.oranization.VehicleDto;
import com.ensolution.ensol.service.organization.VehicleDataService;
import com.ensolution.ensol.service.organization.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
  private final VehicleDataService vehicleDataService;
  
  @Override
  public List<VehicleDto> findAllVehicles() {
    return vehicleDataService.findAll();
  }
}
