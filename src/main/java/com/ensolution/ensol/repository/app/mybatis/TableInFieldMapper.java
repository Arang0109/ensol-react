package com.ensolution.ensol.repository.app.mybatis;

import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.app.query.table.StackMeasurementTableDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableInFieldMapper {
  List<StackMeasurementTableDto> stackMeasurementList(Integer stackId);
  List<ScheduledWorkplaceTableDto> scheduledWorkplaceList();
  List<ScheduledStackTableDto> scheduleStackList(Integer scheduledWorkplaceId);
}
