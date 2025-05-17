package com.ensolution.ensol.dto.app.entity.oranization;

import com.ensolution.ensol.entity.app.organization.Staff;
import com.ensolution.ensol.entity.app.organization.Vehicle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeamDto {
  @Schema(description = "측정 팀 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer teamId;
  
  @Schema(description = "팀 이름", example = "1팀")
  private String teamName;
  
  private Set<StaffDto> staffs;
  private Set<VehicleDto> vehicles;
}