package com.ensolution.ensol.service.schedule.impl;

import com.ensolution.ensol.dto.request.ScheduleRegisterRequestDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.response.ScheduledStackResponseDto;
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
  public ScheduledStackResponseDto findSubSchedules(Integer scheduledWorkplaceId) {
    return ScheduledStackResponseDto.builder()
        .scheduledStackTables(scheduleDataService.findScheduleByScheduledWorkplaceId(scheduledWorkplaceId))
        .scheduledWorkplace(scheduleDataService.getScheduledWorkplaceById(scheduledWorkplaceId))
        .build();
  }
  
  @Override
  @Transactional
  public void registerSchedule(ScheduleRegisterRequestDto schedule) {
    scheduleDataService.saveSchedule(schedule);
  }
}
