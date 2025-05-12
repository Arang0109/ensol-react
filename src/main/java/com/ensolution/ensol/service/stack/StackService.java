package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.request.StackUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface StackService {
  Optional<StackDto> getStackById(Integer stackId);
  List<StackDto> findAllStacks();
  List<StackDto> findStacksByWorkplaceId(Integer workplaceId);
  StackDto registerStack(StackDto stackDto);
  void updateStack(Integer stackId, StackUpdateRequestDto requestDto);
  void deleteStack(Integer stackId);
}