package com.ensolution.ensol.dto.app.query.table;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackMeasurementTableDto {
  private Integer stackMeasurementId;
  private Integer stackId;
  private Integer pollutantId;
  private String cycleType;
  private Double allowValue;
  private Integer oxygenConcentration;
  private String pollutantNameKR;
  private String pollutantNameEN;
  private String pollutantNameHyundai;
  private String method;
}