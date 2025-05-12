package com.ensolution.ensol.entity.app.facility;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stack_info")
@Getter
@Setter
public class StackInformation {
  @Id
  @Column(name = "stack_id", nullable = false)
  private Integer stackId;
  private Double diameter;
  private Double diameter2;
  private Double quantity;
  @Column(name = "height")
  private Double height;
  @Column(name = "stack_shape")
  private String stackShape;
  @Column(name = "stack_direction")
  private String stackDirection;

  @OneToOne
  @MapsId
  @JoinColumn(name = "stack_id")
  private Stack stack;
}