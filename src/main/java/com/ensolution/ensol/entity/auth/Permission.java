package com.ensolution.ensol.entity.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permission")
@Getter
@Setter
public class Permission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permission_id", nullable = false)
  private Integer permissionId;
  @Column(name = "name", nullable = false)
  private String permissionName;
}
