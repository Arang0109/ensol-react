package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;

import java.util.List;

public interface StackMeasurementService {
  List<StackMeasurementTableDto> findStackMeasurementsByStackId(Integer stackId);
  StackMeasurementDto registerStackMeasurement(StackMeasurementDto stackMeasurementDto);
  void deleteStackMeasurements(List<Integer> stackMeasurementsIds);
}
