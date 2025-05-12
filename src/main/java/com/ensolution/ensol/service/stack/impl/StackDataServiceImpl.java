package com.ensolution.ensol.service.stack.impl;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.request.StackUpdateRequestDto;
import com.ensolution.ensol.entity.app.facility.Stack;
import com.ensolution.ensol.entity.app.facility.StackInformation;
import com.ensolution.ensol.mapper.app.facility.StackInformationMapper;
import com.ensolution.ensol.mapper.app.facility.StackMapper;
import com.ensolution.ensol.repository.app.jpa.facility.StackRepository;
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
  private final StackMapper stackMapper;
  private final StackInformationMapper stackInformationMapper;
  
  // 특정 시설 조회
  @Override
  public Optional<StackDto> getStackById(Integer stackId) {
    return stackRepository.findById(stackId).map(stackMapper::toDto);
  }
  
  // 특정 사업장의 시설 목록 조회
  @Override
  public List<StackDto> findAllStacksByWorkplaceId(Integer workplaceId) {
    List<Stack> stacks = stackRepository.findByWorkplace_WorkplaceId(workplaceId);
    return stackMapper.toDtoList(stacks);
  }
  
  // 시설 목록 조회
  @Override
  public List<StackDto> findAllStacks() {
    List<Stack> stacks = stackRepository.findAll();
    return stackMapper.toDtoList(stacks);
  }
  
  @Override
  @Transactional
  public void saveStack(StackDto stackDto) {
    Stack stack = stackMapper.toEntity(stackDto);
    stackRepository.save(stack);
  }

  @Override
  @Transactional
  public void updateStackProfile(StackUpdateRequestDto requestDto) {
    Stack stack = stackRepository.findById(requestDto.getStackId())
        .orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 배출시설 ID: " + requestDto.getStackId()));
    
    stackMapper.updateFromDto(requestDto, stack);
    
    StackInformation stackInfo = stack.getStackInformation();
    stackInformationMapper.updateFromDto(requestDto.getStackInformation(), stackInfo);
  }
  
  @Override
  public void deleteStack(Integer stackId) { stackRepository.deleteById(stackId); }
  
  @Override
  public boolean existsById(Integer stackId) {
    return stackRepository.existsById(stackId);
  }
}