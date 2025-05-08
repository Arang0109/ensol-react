package com.ensolution.ensol.repository.app.jpa.schedule;

import com.ensolution.ensol.entity.app.schedule.GroupedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupedScheduleRepository extends JpaRepository<GroupedSchedule, Integer> {
  List<GroupedSchedule> findByGroupedScheduleIdIn(List<Integer> groupedIds);
}
