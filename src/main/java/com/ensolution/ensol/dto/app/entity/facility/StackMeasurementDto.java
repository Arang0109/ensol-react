package com.ensolution.ensol.dto.app.entity.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackMeasurementDto {
  @Schema(description = "배출시설 측정항목 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackMeasurementId;
  
  @Schema(description = "배출시설 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackId;
  
  @Schema(description = "측정항목 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer pollutantId;
  
  @Schema(description = "측정주기", example = "monthly")
  private String cycleType;
  
  @Schema(description = "허용기준치", example = "150.0")
  private Double allowValue;
  
  @Schema(description = "표준산소농도", example = "12")
  private Integer oxygenConcentration;
}
