package com.ensolution.ensol.dto.response;

import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackDetailResponseDto {
  private StackDto stack;
  private List<StackMeasurementTableDto> stackMeasurements;
}
