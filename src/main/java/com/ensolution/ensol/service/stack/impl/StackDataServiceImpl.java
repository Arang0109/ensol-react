package com.ensolution.ensol.service.stack.impl;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.app.entity.facility.StackInformationDto;
import com.ensolution.ensol.dto.app.query.HistoryDto;
import com.ensolution.ensol.entity.app.facility.Stack;
import com.ensolution.ensol.mapper.app.facility.StackInformationMapper;
import com.ensolution.ensol.mapper.app.facility.StackMapper;
import com.ensolution.ensol.repository.app.jpa.facility.StackInformationRepository;
import com.ensolution.ensol.repository.app.jpa.facility.StackRepository;
import com.ensolution.ensol.repository.app.mybatis.HistoryMapper;
import com.ensolution.ensol.service.stack.StackDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StackDataServiceImpl implements StackDataService {
  private final StackRepository stackRepository;
  private final StackInformationRepository stackInformationRepository;
  private final StackMapper stackMapper;
  private final StackInformationMapper stackInformationMapper;
  private final HistoryMapper historyMapper;

  @Override
  public boolean existsStackById(Integer stackId) {
    return stackRepository.existsById(stackId);
  }

  @Override
  @Transactional
  public void saveStack(StackDto stackDto) {
    Stack stack = stackMapper.toEntity(stackDto);
    stackRepository.save(stack);
  }

  @Override
  @Transactional
  public void updateStack(StackDto stackDto) {
    Stack stack = stackMapper.toEntity(stackDto);
    stackRepository.save(stack);
    
    StackInformationDto stackInfo = stackDto.getStackInformation();
    stackInformationRepository.save(stackInformationMapper.toEntity(stackInfo));
  }

  // 특정 시설 조회
  @Override
  public Optional<StackDto> findStackById(Integer stackId) {
    return stackRepository.findById(stackId).map(stackMapper::toDto);
  }

  // 시설 목록 조회
  @Override
  public List<StackDto> findAllStacks() {
    List<Stack> stacks = stackRepository.findAll();
    return stackMapper.toDtoList(stacks);
  }

  // 특정 사업장의 시설 목록 조회
  @Override
  public List<StackDto> findAllStacksByWorkplaceId(Integer workplaceId) {
    List<Stack> stacks = stackRepository.findByWorkplace_WorkplaceId(workplaceId);
    return stackMapper.toDtoList(stacks);
  }

  @Override
  public void deleteStacks(List<Integer> ids) {
    stackRepository.deleteAllById(ids);
  }
  
  @Override
  public void deleteStack(Integer stackId) { stackRepository.deleteById(stackId); }

  @Override
  public List<HistoryDto> selectStackHistory(Integer stackId) {
    return historyMapper.selectStackHistory(stackId);
  }
}
