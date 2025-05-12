package com.ensolution.ensol.entity.app.schedule;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "measurement_data")
@Getter
@Setter
public class MeasurementData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scheduled_stack_id", nullable = false)
  private Integer scheduledStackId;
  @Column(name = "dynamic_pressure")
  private Double dynamicPressure;
  @Column(name = "static_pressure")
  private Double staticPressure;
  @Column(name = "gas_temperature")
  private Integer gasTemperature;
  
  @OneToOne
  @MapsId
  @JoinColumn(name = "scheduled_stack_id")
  private ScheduledStack scheduledStack;
}
