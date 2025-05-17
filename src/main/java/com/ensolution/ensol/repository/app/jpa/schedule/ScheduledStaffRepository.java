package com.ensolution.ensol.repository.app.jpa.schedule;

import com.ensolution.ensol.entity.app.schedule.ScheduledStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledStaffRepository extends JpaRepository<ScheduledStaff, Integer> {
}
