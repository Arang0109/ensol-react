package com.ensolution.ensol.entity.app.schedule;

import com.ensolution.ensol.common.enums.MeasurementPurpose;
import com.ensolution.ensol.common.enums.ScheduleSubStatus;
import com.ensolution.ensol.entity.app.facility.Stack;
import com.ensolution.ensol.entity.app.organization.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grouped_schedule")
@Getter
@Setter
public class GroupedSchedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "grouped_schedule_id", nullable = false)
  private Integer groupedScheduleId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name= "stack_id", nullable = false)
  private Stack stack;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;
  @Column(name = "measure_date")
  private LocalDate measureDate;
  @Column(name = "measurement_purpose")
  @Enumerated(EnumType.STRING)
  private MeasurementPurpose measurementPurpose;
  @Column(name = "main_reg_number")
  private Integer mainRegNumber;
  @Column(name = "sub_reg_number")
  private Integer subRegNumber;
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ScheduleSubStatus status;
  @Column(name = "reg_date")
  private LocalDate regDate;
  
  @OneToOne(mappedBy = "groupedSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
  private MeasurementData measurementData;
  
  @JsonIgnore
  @OneToMany(mappedBy = "groupedSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ScheduledMeasurement> scheduledMeasurements = new ArrayList<>();
}