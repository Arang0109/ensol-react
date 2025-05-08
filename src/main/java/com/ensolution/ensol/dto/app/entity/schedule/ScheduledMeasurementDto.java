package com.ensolution.ensol.dto.app.entity.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduledMeasurementDto {
  @Schema(description = "측정시설 항목 일정 ID (ScheduledMeasurement의 고유 식별자)", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer scheduledMeasurementId;
  
  @Schema(description = "측정 일정 그룹 ID (같은 시설, 날짜, 팀으로 묶인 일정)", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer groupedScheduleId;
  
  @Schema(description = "측정 항목 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackMeasurementId;
}
