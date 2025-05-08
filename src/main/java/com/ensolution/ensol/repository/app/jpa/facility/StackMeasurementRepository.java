package com.ensolution.ensol.repository.app.jpa.facility;

import com.ensolution.ensol.entity.app.facility.Stack;
import com.ensolution.ensol.entity.app.facility.StackMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StackMeasurementRepository extends JpaRepository<StackMeasurement, Integer> {
  List<StackMeasurement> findStackMeasurementsByStack(Stack stack);
}
