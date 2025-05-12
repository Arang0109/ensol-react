package com.ensolution.ensol.repository.app.jpa.schedule;

import com.ensolution.ensol.entity.app.schedule.ScheduledWorkplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledWorkplaceRepository extends JpaRepository<ScheduledWorkplace, Integer> {
}
