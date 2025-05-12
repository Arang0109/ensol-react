package com.ensolution.ensol.dto.app.entity.schedule;
import com.ensolution.ensol.common.enums.ScheduleSubStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduledStackDto {
  @Schema(description = "배출시설 측정일정 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer scheduledStackId;
  
  @Schema(description = "사업장 측정일정 ID")
  private Integer scheduledWorkplaceId;
  
  @Schema(description = "배출시설 ID")
  private Integer stackId;
  
  @Schema(description = "일정 진행 상태", example = "MEASURING")
  private ScheduleSubStatus status;
  
  @Schema(description = "분석 시작일", example = "2025-05-01")
  private LocalDate analysisStartDate;
  
  @Schema(description = "분석 종료일", example = "2025-05-10")
  private LocalDate analysisEndDate;
  
  private MeasurementDataDto measurementData;
}
