package com.ensolution.ensol.repository.app.jpa.schedule;

import com.ensolution.ensol.entity.app.schedule.ScheduledMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledMeasurementRepository extends JpaRepository<ScheduledMeasurement, Integer> {
}
