package com.ensolution.ensol.service.schedule;

import com.ensolution.ensol.dto.request.ScheduleRegisterRequestDto;
import com.ensolution.ensol.dto.app.entity.schedule.MeasurementDataDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledWorkplaceDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;

import java.util.List;

public interface ScheduleDataService {
  List<ScheduledWorkplaceTableDto> findSchedules();
  List<ScheduledStackTableDto> findScheduleByScheduledWorkplaceId(Integer scheduledWorkplaceId);
  ScheduledWorkplaceDto getScheduledWorkplaceById(Integer scheduledWorkplaceId);
  void saveSchedule(ScheduleRegisterRequestDto responseDto);
  MeasurementDataDto findMeasurementDataByScheduledStackId(Integer scheduleId);
}
