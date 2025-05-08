package com.ensolution.ensol.dto.app.entity.oranization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StaffDto {
  @Schema(description = "측정 인력 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer staffId;
  
  @Schema(description = "측정 인력 이름", example = "강민수")
  private String staffName;
  
  @Schema(description = "직위", example = "사원")
  private String position;
  
  @Schema(description = "부서", example = "자가측정부")
  private String department;
  
  @Schema(description = "측정 분야", example = "대기")
  private String measurementField;
}