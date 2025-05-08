package com.ensolution.ensol.dto.app.entity.schedule;

import com.ensolution.ensol.common.enums.MeasurementPurpose;
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
public class GroupedScheduleDto {
  @Schema(description = "측정일정 그룹 ID (같은 시설, 날짜, 팀으로 묶인 일정)", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer groupedScheduleId;
  
  @Schema(description = "배출시설 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackId;
  
  @Schema(description = "측정팀 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer teamId;
  
  @Schema(description = "측정일", example = "2025-05-01")
  @NotNull
  private LocalDate measureDate;
  
  @Schema(description = "측정용도", example = "SELF")
  private MeasurementPurpose measurementPurpose;
  
  @Schema(description = "주 접수번호", example = "001")
  private Integer mainRegNumber;
  
  @Schema(description = "부 접수번호", example = "01")
  private Integer subRegNumber;
  
  @Schema(description = "일정 진행 상태", example = "MEASURING")
  @NotNull
  private ScheduleSubStatus status;
  
  @Schema(description = "일정 등록일", example = "2025-05-01", accessMode = Schema.AccessMode.READ_ONLY)
  private LocalDate regDate;
  
  private MeasurementDataDto measurementData;
}
