package com.ensolution.ensol.repository.app.jpa.schedule;

import com.ensolution.ensol.entity.app.schedule.ScheduledStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledStackRepository extends JpaRepository<ScheduledStack, Integer> {
}
