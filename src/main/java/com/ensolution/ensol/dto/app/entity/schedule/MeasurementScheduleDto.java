package com.ensolution.ensol.dto.app.entity.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeasurementScheduleDto {
  @Schema(description = "측정시설 항목 일정 ID (MeasurementSchedule의 고유 식별자)", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer measurementScheduleId;
  
  @Schema(description = "배출시설 측정일정 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer scheduledStackId;
  
  @Schema(description = "측정 항목 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackMeasurementId;
}
