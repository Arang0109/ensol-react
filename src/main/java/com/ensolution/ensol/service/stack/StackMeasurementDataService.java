package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;

import java.util.List;

public interface StackMeasurementDataService {
  List<StackMeasurementTableDto> findStackMeasurementsByStackId(Integer stackId);
  void saveStackMeasurement(StackMeasurementDto stackMeasurementDto);
  void deleteStackMeasurements(List<Integer> stackMeasruementIds);
}
