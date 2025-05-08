package com.ensolution.ensol.service.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.GroupedScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.ScheduleTableDto;
import com.ensolution.ensol.dto.app.query.table.SubScheduleTableDto;

import java.util.List;

public interface ScheduleService {
  List<ScheduleTableDto> findAllSchedule();
  List<SubScheduleTableDto> findSubSchedules(List<Integer> groupedScheduleIds);
  ScheduleDto createSchedule(GroupedScheduleDto groupedScheduleDto, List<ScheduledMeasurementDto> scheduledMeasurements);
}
