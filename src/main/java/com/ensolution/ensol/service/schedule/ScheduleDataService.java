package com.ensolution.ensol.service.schedule;

import com.ensolution.ensol.dto.app.entity.schedule.GroupedScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.MeasurementDataDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.ScheduleTableDto;
import com.ensolution.ensol.dto.app.query.table.SubScheduleTableDto;

import java.util.List;

public interface ScheduleDataService {
  List<ScheduleTableDto> findAll();
  List<SubScheduleTableDto> findAllSub(List<Integer> groupedScheduleIds);
  GroupedScheduleDto saveGroupedSchedule(GroupedScheduleDto groupedScheduleDto);
  List<ScheduledMeasurementDto> saveScheduledMeasurements(List<ScheduledMeasurementDto> scheduledMeasurements);
  MeasurementDataDto findMeasurementDataByGroupedScheduleId(Integer scheduleId);
}
