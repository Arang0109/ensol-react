package com.ensolution.ensol.entity.app.facility;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "stack")
@Getter
@Setter
public class Stack {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stack_id", nullable = false)
  private Integer stackId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workplace_id", nullable = false)
  private Workplace workplace;
  @Column(name = "stack_name", nullable = false)
  private String stackName;
  @Column(name = "stack_type")
  private String stackType;
  @Column(name = "stack_size")
  private char stackSize;
  @Column(name = "prevention")
  private String prevention;
  @Column(name = "prevention_capacity")
  private Long preventionCapacity;
  @Column(name = "sems_number")
  private Long semsNumber;
  @Column(name = "note")
  private String note;
  @Column(name = "reg_date")
  private LocalDate regDate;

  @OneToOne(mappedBy = "stack",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private StackInformation stackInformation;
  @OneToMany(mappedBy = "stack")
  private List<StackMeasurement> stackMeasurements;
}
