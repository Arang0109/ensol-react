package com.ensolution.ensol.service.stack.impl;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;
import com.ensolution.ensol.entity.app.facility.StackMeasurement;
import com.ensolution.ensol.mapper.app.facility.StackMeasurementMapper;
import com.ensolution.ensol.repository.app.mybatis.TableInFieldMapper;
import com.ensolution.ensol.repository.app.jpa.facility.StackMeasurementRepository;
import com.ensolution.ensol.common.exception.CustomDKException;
import com.ensolution.ensol.service.stack.StackMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StackMeasurementDataServiceImpl implements StackMeasurementDataService {
  private final StackMeasurementMapper stackMeasurementMapper;
  private final StackMeasurementRepository stackMeasurementRepository;
  private final TableInFieldMapper tableInFieldMapper;

  @Override
  public List<StackMeasurementTableDto> findStackMeasurementsByStackId(Integer stackId) {
    return tableInFieldMapper.stackMeasurementList(stackId);
  }

  @Override
  public void saveStackMeasurement(StackMeasurementDto stackMeasurementDto) {
    try {
      stackMeasurementRepository.save(stackMeasurementMapper.toEntity(stackMeasurementDto));
    } catch (DuplicateKeyException e) {
      throw new CustomDKException("StackMeasurement", "ID", stackMeasurementDto.getStackId().toString(), e);
    }
  }

  @Override
  public void deleteStackMeasurements(List<Integer> ids) {
    try {
      stackMeasurementRepository.deleteAllById(ids);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while deleting stackMeasurements", e);
    }
  }
}
