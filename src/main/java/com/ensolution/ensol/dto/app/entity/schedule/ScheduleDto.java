package com.ensolution.ensol.dto.app.entity.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "측정 일정 DTO")
public class ScheduleDto {
  @Schema(description = "그룹 일정 정보")
  @Valid
  private GroupedScheduleDto groupedSchedule;
  
  @Schema(description = "측정 항목 목록")
  @Valid
  private List<ScheduledMeasurementDto> scheduledMeasurements;
  
  @Schema(description = "자동 생성된 측정 데이터")
  private MeasurementDataDto measurementData;
}