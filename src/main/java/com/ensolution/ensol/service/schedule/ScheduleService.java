package com.ensolution.ensol.service.schedule;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.entity.schedule.CreateScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledStackDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledWorkplaceDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;

import java.util.List;

public interface ScheduleService {
  List<ScheduledWorkplaceTableDto> findAllSchedule();
  List<ScheduledStackTableDto> findSubSchedules(Integer scheduledWorkplaceId);
  void createSchedule(CreateScheduleDto schedule);
}
