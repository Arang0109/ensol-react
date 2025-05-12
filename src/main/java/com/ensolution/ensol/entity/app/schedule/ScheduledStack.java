package com.ensolution.ensol.entity.app.schedule;

import com.ensolution.ensol.common.enums.ScheduleSubStatus;
import com.ensolution.ensol.entity.app.facility.Stack;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scheduled_stack")
@Getter
@Setter
public class ScheduledStack {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scheduled_stack_id", nullable = false)
  private Integer scheduledStackId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "scheduled_workplace_id", nullable = false)
  private ScheduledWorkplace scheduledWorkplace;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name= "stack_id", nullable = false)
  private Stack stack;
  @Column(name = "analysis_start_date")
  private LocalDate analysisStartDate;
  @Column(name = "analysis_end_date")
  private LocalDate analysisEndDate;
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ScheduleSubStatus status;
  
  @OneToOne(mappedBy = "scheduledStack", cascade = CascadeType.ALL, orphanRemoval = true)
  private MeasurementData measurementData;
  
  @JsonIgnore
  @OneToMany(mappedBy = "scheduledStack", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MeasurementSchedule> measurementSchedules = new ArrayList<>();
  
  @PrePersist
  public void prePersist() {
    // 상태 기본값 : MEASURING
    if (status == null) {
      status = ScheduleSubStatus.MEASURING;
    }
  }
}