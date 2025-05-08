package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;

import java.util.List;
import java.util.Optional;

public interface StackMeasurementDataService {
  Optional<StackMeasurementDto> findStackMeasurementById(Integer stackMeasurementId);
  
  List<StackMeasurementTableDto> findStackMeasurementsByStackId(Integer stackId);
  
  void saveStackMeasurement(StackMeasurementDto stackMeasurementDto);
  
  void removeStackMeasurements(List<Integer> ids);
}
