package com.ensolution.ensol.dto.app.entity.schedule;

import com.ensolution.ensol.common.enums.MeasurementPurpose;
import com.ensolution.ensol.common.enums.ScheduleSupStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduledWorkplaceDto {
  @Schema(description = "사업장 측정일정 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer scheduledWorkplaceId;
  
  @Schema(description = "사업장 ID")
  private Integer workplaceId;
  
  @Schema(description = "측정팀 ID")
  private Integer teamId;
  
  @Schema(description = "측정일", example = "2025-05-01")
  private LocalDate measureDate;
  
  @Schema(description = "측정용도", example = "SELF")
  private MeasurementPurpose measurementPurpose;
  
  @Schema(description = "일정 진행 상태", example = "COMPLETED", accessMode = Schema.AccessMode.READ_ONLY)
  private ScheduleSupStatus status;
  
  @Schema(description = "일정 등록일", example = "2025-05-01", accessMode = Schema.AccessMode.READ_ONLY)
  private LocalDate regDate;
}
