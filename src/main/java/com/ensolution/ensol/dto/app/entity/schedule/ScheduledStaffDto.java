package com.ensolution.ensol.dto.app.entity.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduledStaffDto {
  @Schema(description = "해당 일정 측정 인원 ID")
  private Integer scheduledStaffId;
  
  @Schema(description = "측정 일정 ID (사업장)")
  private Integer scheduledWorkplaceId;
  
  @Schema(description = "측정 인원 ID")
  private Integer staffId;
}
