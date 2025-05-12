package com.ensolution.ensol.entity.app.schedule;

import com.ensolution.ensol.entity.app.facility.StackMeasurement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "measurement_schedule")
@Getter
@Setter
public class MeasurementSchedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "measurement_schedule_id", nullable = false)
  private Integer measurementScheduleId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "scheduled_stack_id")
  private ScheduledStack scheduledStack;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_measurement_id")
  private StackMeasurement stackMeasurement;
}
