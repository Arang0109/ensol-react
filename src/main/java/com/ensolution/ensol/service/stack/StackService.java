package com.ensolution.ensol.service.stack;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.app.query.HistoryDto;
import com.ensolution.ensol.dto.app.query.table.StackTableDto;
import com.ensolution.ensol.dto.app.query.IdentityDto;

import java.util.List;
import java.util.Optional;

public interface StackService {
  Optional<StackDto> findStackById(Integer stackId);
  List<StackDto> findAllStacks();
  List<StackDto> findStacksByWorkplaceId(Integer id);
  List<StackTableDto> findStacksOfTable();
  StackDto createStack(StackDto stackDto);
  void updateStack(StackDto stack);
  void removeStacks(List<StackDto> stacks);
  void removeStack(Integer stackId);
  IdentityDto findIds(Integer id);
  List<HistoryDto> findAllHistoryOfStacks(Integer stackId);
}
