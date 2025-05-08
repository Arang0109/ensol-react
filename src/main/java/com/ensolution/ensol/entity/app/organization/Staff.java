package com.ensolution.ensol.entity.app.organization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
