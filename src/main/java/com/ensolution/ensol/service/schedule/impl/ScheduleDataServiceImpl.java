package com.ensolution.ensol.service.schedule.impl;

import com.ensolution.ensol.common.enums.ScheduleSubStatus;
import com.ensolution.ensol.common.enums.ScheduleSupStatus;
import com.ensolution.ensol.dto.app.entity.schedule.GroupedScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.MeasurementDataDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduleDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledMeasurementDto;
import com.ensolution.ensol.dto.app.query.table.ScheduleTableDto;
import com.ensolution.ensol.dto.app.query.table.SubScheduleTableDto;
import com.ensolution.ensol.entity.app.schedule.GroupedSchedule;
import com.ensolution.ensol.entity.app.schedule.MeasurementData;
import com.ensolution.ensol.entity.app.schedule.ScheduledMeasurement;
import com.ensolution.ensol.mapper.app.schedule.GroupedScheduleMapper;
import com.ensolution.ensol.mapper.app.schedule.MeasurementDataMapper;
import com.ensolution.ensol.mapper.app.schedule.ScheduledMeasurementMapper;
import com.ensolution.ensol.repository.app.jpa.schedule.MeasurementDataRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.ScheduledMeasurementRepository;
import com.ensolution.ensol.repository.app.jpa.schedule.GroupedScheduleRepository;
import com.ensolution.ensol.repository.app.mybatis.ScheduleTableMapper;
import com.ensolution.ensol.service.schedule.ScheduleDataService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleDataServiceImpl implements ScheduleDataService {
  private final ScheduleTableMapper scheduleTableMapper;
  
  private final GroupedScheduleRepository groupedScheduleRepository;
  private final GroupedScheduleMapper groupedScheduleMapper;
  private final ScheduledMeasurementRepository scheduledMeasurementRepository;
  private final ScheduledMeasurementMapper scheduledMeasurementMapper;
  private final MeasurementDataMapper measurementDataMapper;
  
  @Override
  public List<ScheduleTableDto> findAll() {
    List<ScheduleTableDto> scheduleList = scheduleTableMapper.scheduleMainList();
    
    for (ScheduleTableDto schedule : scheduleList) {
      String idString = schedule.getGroupedScheduleIds();
      
      if (idString == null || idString.isBlank()) {
        schedule.setSupStatus(ScheduleSupStatus.NONCOMPLETED);
        continue;
      }
      
      List<Integer> groupedIds = Arrays.stream(idString.split(","))
          .map(String::trim)
          .filter(s -> !s.isEmpty())
          .map(Integer::parseInt)
          .toList();
      
      List<GroupedSchedule> groupedSchedules = groupedScheduleRepository.findByGroupedScheduleIdIn(groupedIds);
      List<GroupedScheduleDto> groupedScheduleDtos = groupedScheduleMapper.toDtoList(groupedSchedules);
      
      boolean allCompleted = groupedScheduleDtos.stream()
          .allMatch(g -> g.getStatus() == ScheduleSubStatus.COMPLETED); // 또는 ENUM 비교
      
      schedule.setSupStatus(allCompleted ? ScheduleSupStatus.COMPLETED : ScheduleSupStatus.NONCOMPLETED);
    }
    
    return scheduleList;
  }
  
  @Override
  public List<SubScheduleTableDto> findAllSub(List<Integer> groupedScheduleIds) {
    return scheduleTableMapper.scheduleSubList(groupedScheduleIds);
  }
  
  @Override
  public GroupedScheduleDto saveGroupedSchedule(GroupedScheduleDto groupedScheduleDto) {
    GroupedSchedule entity = groupedScheduleMapper.toEntity(groupedScheduleDto);
    
    MeasurementData data = new MeasurementData();
    data.setGroupedSchedule(entity);
    entity.setMeasurementData(data);
    entity.setRegDate(LocalDate.now());
    
    GroupedSchedule saved = groupedScheduleRepository.save(entity);
    
    return groupedScheduleMapper.toDto(saved);
  }
  
  @Override
  public List<ScheduledMeasurementDto> saveScheduledMeasurements(List<ScheduledMeasurementDto> scheduledMeasurements) {
    List<ScheduledMeasurement> entityList = scheduledMeasurementMapper.toEntityList(scheduledMeasurements);
    List<ScheduledMeasurement> saved = scheduledMeasurementRepository.saveAll(entityList);
    return scheduledMeasurementMapper.toDtoList(saved);
  }
  
  @Override
  public MeasurementDataDto findMeasurementDataByGroupedScheduleId(Integer scheduleId) {
    GroupedSchedule schedule = groupedScheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new EntityNotFoundException("해당 스케줄이 존재하지 않습니다: " + scheduleId));
    
    MeasurementData measurementData = schedule.getMeasurementData();
    
    if (measurementData == null) {
      throw new IllegalStateException("측정 정보가 아직 생성되지 않았습니다.");
    }
    
    return measurementDataMapper.toDto(measurementData);
  }
}
