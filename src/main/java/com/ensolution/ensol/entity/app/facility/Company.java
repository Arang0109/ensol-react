package com.ensolution.ensol.entity.app.facility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "company_id")
  private Integer companyId;
  @Column(name = "company_name", nullable = false)
  private String companyName;
  @Column(length = 45)
  private String address;
  @Column(name = "ceo_name")
  private String ceoName;
  @Column(name = "biz_number", nullable = false)
  private String bizNumber;
  @Column(name = "reg_date")
  private LocalDate regDate;

  @JsonIgnore
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Workplace> workplaces = new ArrayList<>();
}