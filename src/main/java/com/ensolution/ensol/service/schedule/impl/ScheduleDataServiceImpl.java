package com.ensolution.ensol.service.schedule.impl;

import com.ensolution.ensol.dto.request.ScheduleRegisterRequestDto;
import com.ensolution.ensol.dto.app.entity.schedule.MeasurementDataDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledWorkplaceDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;
import com.ensolution.ensol.entity.app.facility.StackMeasurement;
import com.ensolution.ensol.entity.app.facility.Workplace;
import com.ensolution.ensol.entity.app.organization.Staff;
import com.ensolution.ensol.entity.app.schedule.*;
import com.ensolution.ensol.mapper.app.schedule.ScheduledStackMapper;
import com.ensolution.ensol.mapper.app.schedule.ScheduledWorkplaceMapper;
import com.ensolution.ensol.mapper.app.schedule.MeasurementDataMapper;
import com.ensolution.ensol.repository.app.jpa.facility.StackMeasurementRepository;
import com.ensolution.ensol.repository.app.jpa.facility.WorkplaceRepository;
import com.ensolution.ensol.repository.app.jpa.organization.StaffRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.MeasurementScheduleRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.ScheduledStackRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.ScheduledStaffRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.ScheduledWorkplaceRepository;
import com.ensolution.ensol.repository.app.mybatis.TableInFieldMapper;
import com.ensolution.ensol.service.schedule.ScheduleDataService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleDataServiceImpl implements ScheduleDataService {
  private final TableInFieldMapper tableInFieldMapper;
  
  private final WorkplaceRepository workplaceRepository;
  
  private final ScheduledWorkplaceRepository scheduledWorkplaceRepository;
  private final ScheduledWorkplaceMapper scheduledWorkplaceMapper;
  private final ScheduledStackRepository scheduledStackRepository;
  private final ScheduledStackMapper scheduledStackMapper;
  private final MeasurementScheduleRepository measurementScheduleRepository;
  private final MeasurementDataMapper measurementDataMapper;
  private final StaffRepository staffRepository;
  private final StackMeasurementRepository stackMeasurementRepository;
  private final ScheduledStaffRepository scheduledStaffRepository;
  
  @Override
  public ScheduledWorkplaceDto getScheduledWorkplaceById(Integer scheduledWorkplaceId) {
    return scheduledWorkplaceRepository.findById(scheduledWorkplaceId).map(scheduledWorkplaceMapper::toDto)
        .orElseThrow(() -> new IllegalArgumentException("해당 scheduledWorkplace가 존재하지 않습니다."));
  }
  
  @Override
  public List<ScheduledWorkplaceTableDto> findSchedules() {
    return tableInFieldMapper.scheduledWorkplaceList();
  }
  
  @Override
  public List<ScheduledStackTableDto> findScheduleByScheduledWorkplaceId(Integer scheduledWorkplaceId) {
    return tableInFieldMapper.scheduleStackList(scheduledWorkplaceId);
  }
  
  @Override
  @Transactional
  public void saveSchedule(ScheduleRegisterRequestDto dto) {
    try {
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
      List<Integer> stackMeasurementIds = dto.getStackMeasurementIds();
      for (Integer stackMeasurementId : stackMeasurementIds) {
        StackMeasurement stackMeasurement = stackMeasurementRepository
            .findById(stackMeasurementId)
            .orElseThrow(() -> new IllegalArgumentException("측정 항목 ID가 유효하지 않습니다: " + stackMeasurementId));
        
        MeasurementSchedule schedule = new MeasurementSchedule();
        schedule.setScheduledStack(savedStack);
        schedule.setStackMeasurement(stackMeasurement);
        measurementScheduleRepository.save(schedule);
      }
      
      // 4. 측정인력 저장
      List<Integer> staffIds = dto.getStaffIds();
      for (Integer staffId : staffIds) {
        Staff staff = staffRepository
            .findById(staffId)
            .orElse(null);
        
        ScheduledStaff scheduledStaff = new ScheduledStaff();
        scheduledStaff.setScheduledWorkplace(savedWorkplace);
        scheduledStaff.setStaff(staff);
        scheduledStaffRepository.save(scheduledStaff);
      }
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("해당 사업장, 팀, 측정일 조합은 이미 등록된 일정입니다.");
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
