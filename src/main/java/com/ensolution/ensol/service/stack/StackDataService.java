package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.request.StackUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface StackDataService {
  Optional<StackDto> getStackById(Integer stackId);
  List<StackDto> findAllStacksByWorkplaceId(Integer workplaceId);
  List<StackDto> findAllStacks();
  void saveStack(StackDto stackDto);
  void updateStackProfile(StackUpdateRequestDto requestDto);
  void deleteStack(Integer stackId);
  boolean existsById(Integer stackId);
}