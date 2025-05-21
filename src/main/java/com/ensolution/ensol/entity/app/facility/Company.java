package com.ensolution.ensol.entity.app.facility;

import com.ensolution.ensol.common.util.ValidationPreprocessor;
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
  @Column(name = "company_name", nullable = false, unique = true, length = 45)
  private String companyName;
  @Column(name = "company_normalized_name", nullable = false, unique = true, length = 45)
  private String companyNormalizedName;
  @Column(length = 45)
  private String address;
  @Column(name = "ceo_name", length = 45)
  private String ceoName;
  @Column(name = "biz_number", nullable = false)
  private String bizNumber;
  @Column(name = "reg_date", updatable = false)
  private LocalDate regDate;

  @JsonIgnore
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Workplace> workplaces = new ArrayList<>();
  
  @PrePersist
  public void normalizeFields() {
    this.companyNormalizedName = ValidationPreprocessor.toLowerTrimmed(
        ValidationPreprocessor.normalizeInput(this.companyName)
    );
    
    this.regDate = LocalDate.now();
  }
  
  @PreUpdate
  public void prePersist() {
    this.companyNormalizedName = ValidationPreprocessor.toLowerTrimmed(
        ValidationPreprocessor.normalizeInput(this.companyName)
    );
  }
}