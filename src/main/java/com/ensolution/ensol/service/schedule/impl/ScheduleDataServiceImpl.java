package com.ensolution.ensol.service.schedule.impl;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.entity.schedule.CreateScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.MeasurementDataDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;
import com.ensolution.ensol.entity.app.facility.StackMeasurement;
import com.ensolution.ensol.entity.app.facility.Workplace;
import com.ensolution.ensol.entity.app.schedule.ScheduledStack;
import com.ensolution.ensol.entity.app.schedule.ScheduledWorkplace;
import com.ensolution.ensol.entity.app.schedule.MeasurementData;
import com.ensolution.ensol.entity.app.schedule.MeasurementSchedule;
import com.ensolution.ensol.mapper.app.facility.StackMeasurementMapper;
import com.ensolution.ensol.mapper.app.facility.WorkplaceMapper;
import com.ensolution.ensol.mapper.app.schedule.MeasurementScheduleMapper;
import com.ensolution.ensol.mapper.app.schedule.ScheduledStackMapper;
import com.ensolution.ensol.mapper.app.schedule.ScheduledWorkplaceMapper;
import com.ensolution.ensol.mapper.app.schedule.MeasurementDataMapper;
import com.ensolution.ensol.repository.app.jpa.facility.StackMeasurementRepository;
import com.ensolution.ensol.repository.app.jpa.facility.StackRepository;
import com.ensolution.ensol.repository.app.jpa.facility.WorkplaceRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.MeasurementScheduleRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.ScheduledStackRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.ScheduledWorkplaceRepository;
import com.ensolution.ensol.repository.app.mybatis.ScheduleTableMapper;
import com.ensolution.ensol.service.schedule.ScheduleDataService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleDataServiceImpl implements ScheduleDataService {
  private final ScheduleTableMapper scheduleTableMapper;
  
  private final WorkplaceRepository workplaceRepository;
  private final StackRepository stackRepository;
  
  private final ScheduledWorkplaceRepository scheduledWorkplaceRepository;
  private final ScheduledWorkplaceMapper scheduledWorkplaceMapper;
  private final ScheduledStackRepository scheduledStackRepository;
  private final ScheduledStackMapper scheduledStackMapper;
  private final MeasurementScheduleRepository measurementScheduleRepository;
  private final MeasurementDataMapper measurementDataMapper;
  private final StackMeasurementRepository stackMeasurementRepository;
  
  @Override
  public List<ScheduledWorkplaceTableDto> findSchedules() {
    return scheduleTableMapper.scheduledWorkplaceList();
  }
  
  @Override
  public List<ScheduledStackTableDto> findScheduleByScheduledWorkplaceId(Integer scheduledWorkplaceId) {
    return scheduleTableMapper.scheduleStackList(scheduledWorkplaceId);
  }
  
  @Override
  @Transactional
  public void saveSchedule(CreateScheduleDto dto) {
    // 1. 사업장 저장
    Workplace workplace = workplaceRepository.findById(dto.getScheduledWorkplace().getWorkplaceId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사업장입니다"));
    
    ScheduledWorkplace scheduledWorkplace = scheduledWorkplaceMapper.toEntity(dto.getScheduledWorkplace());
    scheduledWorkplace.setWorkplace(workplace);
    ScheduledWorkplace savedWorkplace = scheduledWorkplaceRepository.save(scheduledWorkplace);
    
    // 2. 스택 저장
    ScheduledStack stack = scheduledStackMapper.toEntity(dto.getScheduledStack());
    stack.setScheduledWorkplace(savedWorkplace);
    ScheduledStack savedStack = scheduledStackRepository.save(stack);
    
    // 3. 측정일정 저장
    List<StackMeasurementDto> stackMeasurementList = dto.getStackMeasurements();
    for (StackMeasurementDto smDto : stackMeasurementList) {
      StackMeasurement stackMeasurement = stackMeasurementRepository
          .findById(smDto.getStackMeasurementId())
          .orElseThrow(() -> new IllegalArgumentException("측정 항목 ID가 유효하지 않습니다: " + smDto.getStackMeasurementId()));
      
      MeasurementSchedule schedule = new MeasurementSchedule();
      schedule.setScheduledStack(savedStack);
      schedule.setStackMeasurement(stackMeasurement);
      measurementScheduleRepository.save(schedule);
    }
  }
  
  @Override
  public MeasurementDataDto findMeasurementDataByScheduledStackId(Integer scheduledStackId) {
    ScheduledStack schedule = scheduledStackRepository.findById(scheduledStackId)
        .orElseThrow(() -> new EntityNotFoundException("해당 스케줄이 존재하지 않습니다: " + scheduledStackId));
    
    MeasurementData measurementData = schedule.getMeasurementData();
    
    if (measurementData == null) {
      throw new IllegalStateException("측정 정보가 아직 생성되지 않았습니다.");
    }
    
    return measurementDataMapper.toDto(measurementData);
  }
}
