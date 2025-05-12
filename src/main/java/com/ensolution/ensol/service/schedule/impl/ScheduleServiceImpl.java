package com.ensolution.ensol.service.schedule.impl;

import com.ensolution.ensol.dto.app.entity.schedule.CreateScheduleDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;
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
  public List<ScheduledWorkplaceTableDto> findAllSchedule() {
    return scheduleDataService.findSchedules();
  }
  
  @Override
  public List<ScheduledStackTableDto> findSubSchedules(Integer scheduledWorkplaceId) {
    return scheduleDataService.findScheduleByScheduledWorkplaceId(scheduledWorkplaceId);
  }
  
  @Override
  @Transactional
  public void createSchedule(CreateScheduleDto createSchedule) {
    scheduleDataService.saveSchedule(createSchedule);
  }
}
