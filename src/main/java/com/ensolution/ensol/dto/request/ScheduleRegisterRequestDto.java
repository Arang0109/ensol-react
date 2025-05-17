package com.ensolution.ensol.dto.request;

import com.ensolution.ensol.dto.app.entity.schedule.ScheduledStackDto;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduledWorkplaceDto;
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
public class ScheduleRegisterRequestDto {
  @Schema(description = "일정 정보(사업장)")
  @Valid
  private ScheduledWorkplaceDto scheduledWorkplace;
  
  @Schema(description = "일정 정보(배출 시설)")
  @Valid
  private ScheduledStackDto scheduledStack;
  
  @Schema(description = "측정 항목 ID List")
  @Valid
  private List<Integer> stackMeasurementIds; // measuremntSchedule 생성
  
  @Schema(description = "측정 인력 ID List")
  @Valid
  private List<Integer> staffIds; // scheduledStaff 생성
}