package com.ensolution.ensol.entity.app.organization;

import com.ensolution.ensol.entity.app.schedule.ScheduledWorkplace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "staff_id")
  private Integer staffId;
  
  @Column(name = "staff_name", nullable = false)
  private String staffName;
  
  private String position;
  private String department;
  
  @Column(name = "measurement_field")
  private String measurementField;
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "scheduled_staff",
      joinColumns = @JoinColumn(name = "scheduled_staff_id"),
      inverseJoinColumns = @JoinColumn(name = "scheduled_workplace_id"))
  private Set<ScheduledWorkplace> scheduledWorkplaces = new HashSet<>();
}
