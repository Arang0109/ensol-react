package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;

import java.util.List;
import java.util.Optional;

public interface StackMeasurementService {
  Optional<StackMeasurementDto> findStackMeasurementById(Integer id);
  List<StackMeasurementTableDto> findStackMeasurementsByStackId(Integer stack_id);
  StackMeasurementDto createStackMeasurement(StackMeasurementDto stackMeasurement);
  void removeStackMeasurements(List<Integer> stackMeasurementsIds);
}
