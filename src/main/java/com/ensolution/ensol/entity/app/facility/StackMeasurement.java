package com.ensolution.ensol.entity.app.facility;

import com.ensolution.ensol.entity.app.pollutant.Pollutant;
import com.ensolution.ensol.entity.app.schedule.ScheduledMeasurement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stack_measurement")
@Getter
@Setter
public class StackMeasurement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stack_measurement_id", nullable = false)
  private Integer stackMeasurementId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id", nullable = false)
  private Stack stack;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pollutant_id", nullable = false)
  private Pollutant pollutant;
  @Column(name = "cycle_type", nullable = false)
  private String cycleType;
  @Column(name = "allow_value")
  private String allowValue;
  @Column(name = "oxygen_concentration")
  private String oxygenConcentration;
  @Column(name = "is_completed")
  private boolean isCompleted;
  @Column(name = "is_measured")
  private boolean isMeasured;

  @JsonIgnore
  @OneToMany(mappedBy = "stackMeasurement")
  private List<ScheduledMeasurement> scheduledMeasurements;
}
