package com.ensolution.ensol.entity.app.facility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workplace")
@Getter
@Setter
public class Workplace {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "workplace_id", nullable = false)
  private Integer workplaceId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id" ,nullable = false)
  private Company company;
  @Column(name = "workplace_name", nullable = false)
  private String workplaceName;
  @Column(name = "workplace_size")
  private char workplaceSize;
  private String address;
  @Column(name = "main_production")
  private String mainProduction;
  @Column(name = "business_type")
  private String businessType;
  @Column(name = "reg_date")
  private LocalDate regDate;

  @JsonIgnore
  @OneToMany(mappedBy = "workplace")
  private List<Stack> stacks = new ArrayList<>();
}