package com.ensolution.ensol.entity.app.pollutant;

import com.ensolution.ensol.entity.app.facility.StackMeasurement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pollutant")
@Getter
@Setter
public class Pollutant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pollutant_id", nullable = false)
  private Integer pollutantId;
  @Column(name = "pollutant_name_kr", nullable = false)
  private String pollutantNameKR;
  @Column(name = "pollutant_name_en")
  private String pollutantNameEN;
  @Column(name = "pollutant_name_hyundai")
  private String pollutantNameHyundai;
  private String method;
  @Column(name = "sampling_time")
  private Integer samplingTime;
  @Column(name = "sampling_volume")
  private Integer samplingVolume;
  @Column(name = "analysis_equipment")
  private String analysisEquipment;
  @Column(name = "legislation_number")
  private String legislationNumber;

  @OneToMany(mappedBy = "pollutant")
  private List<StackMeasurement> stackMeasurements;
}
