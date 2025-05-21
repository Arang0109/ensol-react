package com.ensolution.ensol.repository.app.jpa.facility;

import com.ensolution.ensol.entity.app.facility.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
  boolean existsByCompanyNormalizedName(String companyName);
  boolean existsByBizNumber(String bizNumber);
}