package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.app.query.HistoryDto;

import java.util.List;
import java.util.Optional;

public interface StackDataService {
  boolean existsStackById(Integer stackId);
  void saveStack(StackDto stackDto);
  void updateStack(StackDto stackDto);
  // 특정 시설 조회
  Optional<StackDto> findStackById(Integer stackId);
  // 시설 목록 조회
  List<StackDto> findAllStacks();
  // 특정 사업장의 시설 목록 조회
  List<StackDto> findAllStacksByWorkplaceId(Integer workplaceId);
  void deleteStacks(List<Integer> ids);
  void deleteStack(Integer stackId);
  List<HistoryDto> selectStackHistory(Integer stackId);
}
