package com.ensolution.ensol.service.stack.impl;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;
import com.ensolution.ensol.common.exception.CustomDKException;
import com.ensolution.ensol.service.stack.StackMeasurementDataService;
import com.ensolution.ensol.service.stack.StackMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StackMeasurementServiceImpl implements StackMeasurementService {
  private final StackMeasurementDataService stackMeasurementDataService;

  @Override
  public Optional<StackMeasurementDto> findStackMeasurementById(Integer stackMeasurementId) {
   return stackMeasurementDataService.findStackMeasurementById(stackMeasurementId);
  }

  @Override
  public List<StackMeasurementTableDto> findStackMeasurementsByStackId(Integer stackId) {;
    return stackMeasurementDataService.findStackMeasurementsByStackId(stackId);
  }

  @Override
  public StackMeasurementDto createStackMeasurement(StackMeasurementDto stackMeasurementDto) {
    try {
      stackMeasurementDto.setCompleted(false);
      stackMeasurementDataService.saveStackMeasurement(stackMeasurementDto);
    } catch (DuplicateKeyException e) {
      throw new CustomDKException("StackMeasurement", "ID", stackMeasurementDto.getStackId().toString(), e);
    }
    
    return stackMeasurementDto;
  }

  @Override
  public void removeStackMeasurements(List<Integer> stackMeasurementsIds) {
    stackMeasurementDataService.removeStackMeasurements(stackMeasurementsIds);
  }
}
