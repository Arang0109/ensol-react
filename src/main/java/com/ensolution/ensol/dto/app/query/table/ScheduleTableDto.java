package com.ensolution.ensol.dto.app.query.table;

import com.ensolution.ensol.common.enums.MeasurementPurpose;
import com.ensolution.ensol.common.enums.ScheduleSupStatus;
import com.ensolution.ensol.dto.app.entity.schedule.GroupedScheduleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "측정일정 리스트")
public class ScheduleTableDto {
  @Schema(description = "포함된 측정 일정 IDS")
  private String groupedScheduleIds;
  
  @Schema(description = "주 접수번호", example = "001")
  private Integer mainRegNumber;
  
  @Schema(description = "측정용도 (SELF / REFERENCE)", example = "SELF")
  private MeasurementPurpose measurementPurpose;
  
  @Schema(description = "종합 상태 (COMPLETED / NONCOMPLETED)")
  private ScheduleSupStatus supStatus;
  
  @Schema(description = "측정대상 사업장 ID", example = "8")
  private Integer workplaceId;
  
  @Schema(description = "측정대상 사업장", example = "현대자동차(주) 울산공장")
  private String workplaceName;
  
  @Schema(description = "사업장 주소", example = "부산진구 동평로 291번길 30")
  private String address;
  
  @Schema(description = "배출시설", example = "stack 195, stack 196")
  private String stackNames;
  
  @Schema(description = "측정일(채취일)", example = "2025-05-01")
  private LocalDate measureDate;
  
  @Schema(description = "측정 팀 ID", example = "2")
  private Integer teamId;
  
  @Schema(description = "측정 팀", example = "2팀")
  private String teamName;
}