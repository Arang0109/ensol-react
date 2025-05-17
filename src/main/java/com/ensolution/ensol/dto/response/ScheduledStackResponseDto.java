package com.ensolution.ensol.dto.response;

import com.ensolution.ensol.dto.app.entity.schedule.ScheduledWorkplaceDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduledStackResponseDto {
  private List<ScheduledStackTableDto> scheduledStackTables;
  private ScheduledWorkplaceDto scheduledWorkplace;
}
