package com.ensolution.ensol.service.stack.impl;

import com.ensolution.ensol.dto.app.query.HistoryDto;
import com.ensolution.ensol.dto.app.query.IdentityDto;
import com.ensolution.ensol.repository.app.mybatis.IdentityMapper;
import com.ensolution.ensol.repository.app.mybatis.TableInformationMapper;
import com.ensolution.ensol.common.exception.CustomDKException;
import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.app.query.table.StackTableDto;
import com.ensolution.ensol.service.pollutant.PollutantDataService;
import com.ensolution.ensol.service.stack.StackDataService;
import com.ensolution.ensol.service.stack.StackService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StackServiceImpl implements StackService {
  private final StackDataService stackDataService;
  private final PollutantDataService pollutantDataService;
  private final TableInformationMapper tableInformationMapper;
  private final IdentityMapper identityMapper;

  @Override
  public Optional<StackDto> findStackById(Integer stackId) {
    return stackDataService.findStackById(stackId);
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

  // 전체 시설 테이블 목록
  @Override
  public List<StackTableDto> findStacksOfTable() {
    return tableInformationMapper.selectStacksOfTable();
  }

  @Override
  public StackDto createStack(StackDto stackDto) {
    try {
      stackDto.setRegDate(LocalDate.now());
      stackDataService.saveStack(stackDto);
    } catch (DuplicateKeyException e) {
      throw new CustomDKException("stack", "Name", stackDto.getStackName(), e);
    }
    
    return stackDto;
  }

  @Override
  public void updateStack(StackDto stackDto) {
    if (!stackDataService.existsStackById(stackDto.getStackId())) {
      throw new IllegalArgumentException("Stack with Name " + stackDto.getStackName() + " does not exist.");
    }

    stackDataService.updateStack(stackDto);
  }

  @Override
  public void removeStacks(List<StackDto> stacks) {
    if (stacks == null || stacks.isEmpty()) {
      throw new IllegalArgumentException("Input list cannot be null or empty");
    }

    List<Integer> ids = new ArrayList<>();

    for (StackDto stackDto : stacks) {
      if (stackDto == null) {
        throw new IllegalArgumentException("StackDto cannot be null");
      }
      ids.add(stackDto.getStackId());
    }

    try {
      stackDataService.deleteStacks(ids);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while deleting stacks", e);
    }
  }
  
  @Override
  public void removeStack(Integer stackId) {
    stackDataService.deleteStack(stackId);
  }

  @Override
  public IdentityDto findIds(Integer stackId) {
    return identityMapper.getIds(stackId);
  }

  @Override
  public List<HistoryDto> findAllHistoryOfStacks(Integer stackId) {
    List<HistoryDto> histories = stackDataService.selectStackHistory(stackId);

    return historyFormater(histories);
  }

  private List<HistoryDto> historyFormater(List<HistoryDto> histories) {
    for (HistoryDto history : histories) {
      StringBuilder pollutants = new StringBuilder();
      String pollutantIds = history.getPollutantIds();

      for (String pollutantId : pollutantIds.split(",")) {
        Integer id = Integer.parseInt(pollutantId);
        String pollutantName = pollutantDataService.findPollutantById(id).getPollutantNameKR();
        pollutants.append(pollutantName).append(", ");
      }

      if (!pollutants.isEmpty()) {
        pollutants.setLength(pollutants.length() - 2);
      }

      history.setPollutantIds(pollutants.toString());
    }
    return histories;
  }
}
