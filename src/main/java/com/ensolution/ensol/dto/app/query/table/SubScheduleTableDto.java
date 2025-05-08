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
@Schema(description = "측정일정 리스트")
public class SubScheduleTableDto {
  @Schema(description = "측정 일정 ID")
  private Integer groupedScheduleId;
  
  @Schema(description = "배출시설 ID", example = "9")
  private Integer stackId;
  
  @Schema(description = "배출시설", example = "stack 195")
  private String stackName;
  
  @Schema(description = "주 접수번호", example = "001")
  private Integer mainRegNumber;
  
  @Schema(description = "부 접수번호", example = "01")
  private Integer subRegNumber;
  
  @Schema(description = "분석 상태")
  private ScheduleSubStatus subStatus;
  
  @Schema(description = "측정 항목", example = "먼지, 질소산화물")
  private String pollutantNames;
  
  @Schema(description = "등록일", example = "2025-05-01")
  private LocalDate regDate;
}