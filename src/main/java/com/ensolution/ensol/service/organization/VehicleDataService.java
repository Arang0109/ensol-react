package com.ensolution.ensol.service.organization;

import com.ensolution.ensol.dto.app.entity.oranization.VehicleDto;

import java.util.List;

public interface VehicleDataService {
  List<VehicleDto> findAll();
}
