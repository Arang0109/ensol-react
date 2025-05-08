package com.ensolution.ensol.entity.app.organization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vehicle_id")
  private Integer vehicleId;
  
  @Column(name = "vehicle_number", nullable = false)
  private String vehicleNumber;
  
  @Column(name = "model_name")
  private String modelName;
  
  private String department;
}
