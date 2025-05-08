package com.ensolution.ensol.dto.response;

import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkplaceDetailResponseDto {
  private WorkplaceDto workplace;
  private List<StackDto> stacks;
}
