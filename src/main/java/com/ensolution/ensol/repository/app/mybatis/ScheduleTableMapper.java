package com.ensolution.ensol.repository.app.mybatis;

import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledStackTableDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleTableMapper {
  List<ScheduledWorkplaceTableDto> scheduledWorkplaceList();
  List<ScheduledStackTableDto> scheduleStackList(Integer scheduledWorkplaceId);
}
