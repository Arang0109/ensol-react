package com.ensolution.ensol.entity.app.schedule;

import com.ensolution.ensol.entity.app.organization.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scheduled_staff")
@Getter
@Setter
public class ScheduledStaff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scheduled_staff_id", nullable = false)
  private Integer scheduledStaffId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "scheduled_workplace_id", nullable = false)
  private ScheduledWorkplace scheduledWorkplace;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name= "staff_id", nullable = false)
  private Staff staff;
}