package com.ensolution.ensol.entity.app.schedule;

import com.ensolution.ensol.common.enums.MeasurementPurpose;
import com.ensolution.ensol.common.enums.ScheduleSupStatus;
import com.ensolution.ensol.entity.app.facility.Workplace;
import com.ensolution.ensol.entity.app.organization.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "scheduled_workplace")
@Getter
@Setter
public class ScheduledWorkplace {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scheduled_workplace_id", nullable = false)
  private Integer scheduledWorkplaceId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name= "workplace_id", nullable = false)
  private Workplace workplace;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;
  
  @Column(name = "measure_date")
  private LocalDate measureDate;
  
  @Column(name = "measurement_purpose")
  @Enumerated(EnumType.STRING)
  private MeasurementPurpose measurementPurpose;
  
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ScheduleSupStatus status;
  
  @Column(name = "reg_date")
  private LocalDate regDate;
  
  // 초기화 코드 //
  @PrePersist
  public void prePersist() {
    // 상태 기본값 : NONCOMPLETED
    if (status == null) {
      status = ScheduleSupStatus.NONCOMPLETED;
    }
    // 등록일은 항상 현재 날짜로 설정
    if (regDate == null) {
      regDate = LocalDate.now();
    }
  }
}