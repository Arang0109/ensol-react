package com.ensolution.ensol.service.stack.impl;

import com.ensolution.ensol.dto.request.StackUpdateRequestDto;
import com.ensolution.ensol.common.exception.CustomDKException;
import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.service.stack.StackDataService;
import com.ensolution.ensol.service.stack.StackService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StackServiceImpl implements StackService {
  private final StackDataService stackDataService;

  @Override
  public Optional<StackDto> getStackById(Integer stackId) {
    return stackDataService.getStackById(stackId);
  }

  @Override
  public List<StackDto> findAllStacks() {
    return stackDataService.findAllStacks();
  }

  // 특정 사업장의 시설 테이블 목록
  @Override
  public List<StackDto> findStacksByWorkplaceId(Integer workplaceId) {
    return stackDataService.findAllStacksByWorkplaceId(workplaceId);
  }

  @Override
  public StackDto registerStack(StackDto stackDto) {
    try {
      stackDto.setRegDate(LocalDate.now());
      stackDataService.saveStack(stackDto);
    } catch (DuplicateKeyException e) {
      throw new CustomDKException("stack", "Name", stackDto.getStackName(), e);
    }
    
    return stackDto;
  }

  @Override
  public void updateStack(Integer stackId, StackUpdateRequestDto requestDto) {
    if (!stackDataService.existsById(stackId)) {
      throw new IllegalArgumentException("Stack with Name " + requestDto.getStackName() + " does not exist.");
    }

    requestDto.setStackId(stackId);
    stackDataService.updateStackProfile(requestDto);
  }
  
  @Override
  public void deleteStack(Integer stackId) {
    
    try {
      stackDataService.deleteStack(stackId);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while deleting companies", e);
    }
  }
}