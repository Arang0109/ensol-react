package com.ensolution.ensol.dto.app.entity.oranization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VehicleDto {
  @Schema(description = "차량 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer vehicleId;
  
  @Schema(description = "차량 번호", example = "90주 2445")
  private String vehicleNumber;
  
  @Schema(description = "차량 모델", example = "그랜드 스타렉스")
  private String modelName;
  
  @Schema(description = "부서", example = "자가측정부")
  private String department;
}