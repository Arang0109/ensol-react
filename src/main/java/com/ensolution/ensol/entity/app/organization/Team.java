package com.ensolution.ensol.entity.app.organization;

import com.ensolution.ensol.entity.app.schedule.GroupedSchedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "team")
@Getter @Setter
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "team_id", nullable = false)
  private Integer teamId;
  
  @Column(name = "team_name", nullable = false)
  private String teamName;

  @JsonIgnore
  @OneToMany(mappedBy = "team")
  private List<GroupedSchedule> groupedSchedules;
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "team_staff",
      joinColumns = @JoinColumn(name = "team_id"),
      inverseJoinColumns = @JoinColumn(name = "staff_id"))
  private Set<Staff> staffs = new HashSet<>();
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "team_vehicle",
      joinColumns = @JoinColumn(name = "team_id"),
      inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
  private Set<Vehicle> vehicles = new HashSet<>();
}