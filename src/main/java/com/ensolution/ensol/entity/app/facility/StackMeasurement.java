package com.ensolution.ensol.entity.app.facility;

import com.ensolution.ensol.common.enums.CycleType;
import com.ensolution.ensol.entity.app.pollutant.Pollutant;
import com.ensolution.ensol.entity.app.schedule.MeasurementSchedule;
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
  @Enumerated(EnumType.STRING)
  private CycleType cycleType;
  @Column(name = "allow_value")
  private String allowValue;
  @Column(name = "oxygen_concentration")
  private String oxygenConcentration;

  @JsonIgnore
  @OneToMany(mappedBy = "stackMeasurement")
  private List<MeasurementSchedule> measurementSchedules;
}