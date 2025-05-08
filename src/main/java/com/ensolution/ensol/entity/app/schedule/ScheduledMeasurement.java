package com.ensolution.ensol.entity.app.schedule;

import com.ensolution.ensol.entity.app.facility.StackMeasurement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scheduled_measurement")
@Getter
@Setter
public class ScheduledMeasurement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scheduled_measurement_id", nullable = false)
  private Integer scheduledMeasurementId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grouped_schedule_id")
  private GroupedSchedule groupedSchedule;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_measurement_id")
  private StackMeasurement stackMeasurement;
}
