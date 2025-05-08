package com.ensolution.ensol.dto.app.entity.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeasurementDataDto {
  @Schema(description = "측정 일정 그룹 ID (같은 시설, 날짜, 팀으로 묶인 일정)", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer groupedScheduleId;
  
  @Schema(description = "동압", example = "9.999")
  private Double dynamicPressure;
  
  @Schema(description = "정압", example = "-9.999")
  private Double staticPressure;
  
  @Schema(description = "가스 온도", example = "10")
  private Integer gasTemperature;
}
