package com.ensolution.ensol.dto.app.entity.schedule;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
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
public class CreateScheduleDto {
  @Schema(description = "일정 정보(사업장)")
  @Valid
  private ScheduledWorkplaceDto scheduledWorkplace;
  
  @Schema(description = "일정 정보(배출 시설)")
  @Valid
  private ScheduledStackDto scheduledStack;
  
  @Schema(description = "측정 항목 정보")
  @Valid
  private List<StackMeasurementDto> stackMeasurements;
}