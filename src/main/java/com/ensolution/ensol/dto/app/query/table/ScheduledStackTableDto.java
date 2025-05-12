package com.ensolution.ensol.dto.app.query.table;

import com.ensolution.ensol.common.enums.ScheduleSubStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "배출시설 측정일정 리스트")
public class ScheduledStackTableDto {
  @Schema(description = "사업장 측정일정 ID")
  private Integer scheduledWorkplaceId;
  
  @Schema(description = "배출시설 측정일정 ID")
  private Integer scheduledStackId;
  
  @Schema(description = "측정 항목 ID LIST")
  private String stackMeasurementIds;
  
  @Schema(description = "배출시설 ID", example = "9")
  private Integer stackId;
  
  @Schema(description = "배출시설", example = "stack 195")
  private String stackName;
  
  @Schema(description = "분석 상태")
  private ScheduleSubStatus status;
  
  @Schema(description = "분석 시작일")
  private LocalDate analysisStartDate;
  
  @Schema(description = "분석 종료일")
  private LocalDate analysisEndDate;
  
  @Schema(description = "측정 항목", example = "먼지, 질소산화물")
  private String pollutantNames;
}