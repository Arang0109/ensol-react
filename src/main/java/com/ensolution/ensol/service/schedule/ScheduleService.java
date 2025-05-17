package com.ensolution.ensol.service.schedule;

import com.ensolution.ensol.dto.request.ScheduleRegisterRequestDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.response.ScheduledStackResponseDto;

import java.util.List;

public interface ScheduleService {
  List<ScheduledWorkplaceTableDto> findAllSchedule();
  ScheduledStackResponseDto findSubSchedules(Integer scheduledWorkplaceId);
  void registerSchedule(ScheduleRegisterRequestDto schedule);
}
