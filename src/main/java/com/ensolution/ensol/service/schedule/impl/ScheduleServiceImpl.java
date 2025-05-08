package com.ensolution.ensol.service.schedule.impl;

import com.ensolution.ensol.dto.app.entity.schedule.GroupedScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.ScheduleTableDto;
import com.ensolution.ensol.dto.app.query.table.SubScheduleTableDto;
import com.ensolution.ensol.service.schedule.ScheduleDataService;
import com.ensolution.ensol.service.schedule.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
  private final ScheduleDataService scheduleDataService;
  
  @Override
  public List<ScheduleTableDto> findAllSchedule() {
    return scheduleDataService.findAll();
  }
  
  @Override
  public List<SubScheduleTableDto> findSubSchedules(List<Integer> groupedScheduleIds) {
    return scheduleDataService.findAllSub(groupedScheduleIds);
  }
  
  
  @Override
  @Transactional
  public ScheduleDto createSchedule(
      GroupedScheduleDto groupedScheduleDto,
      List<ScheduledMeasurementDto> scheduledMeasurements) {
    // 1. Grouped Schedule 등록
    GroupedScheduleDto savedSchedule = scheduleDataService.saveGroupedSchedule(groupedScheduleDto);
    Integer scheduleId = savedSchedule.getGroupedScheduleId();
    
    // 2. Scheduled Measurements 등록
    scheduledMeasurements.forEach(prop -> prop.setGroupedScheduleId(scheduleId));
    List<ScheduledMeasurementDto> savedMeasurements = scheduleDataService.saveScheduledMeasurements(scheduledMeasurements);
    
    ScheduleDto schedule = new ScheduleDto();
    schedule.setGroupedSchedule(savedSchedule);
    schedule.setScheduledMeasurements(savedMeasurements);
    schedule.setMeasurementData(scheduleDataService.findMeasurementDataByGroupedScheduleId(scheduleId));
    
    return schedule;
  }
}
