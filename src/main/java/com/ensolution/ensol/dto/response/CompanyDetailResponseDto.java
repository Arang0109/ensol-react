package com.ensolution.ensol.dto.response;

import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDetailResponseDto {
  private CompanyDto company;
  private List<WorkplaceDto> workplaces;
}
