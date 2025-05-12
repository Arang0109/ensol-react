package com.ensolution.ensol.service.company;

import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.dto.request.CompanyUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface CompanyDataService {
  Optional<CompanyDto> getCompanyById(Integer companyId);
  List<CompanyDto> findAllCompanies();
  void saveCompany(CompanyDto companyDto);
  void updateCompanyProfile(CompanyUpdateRequestDto requestDto);
  void deleteCompany(Integer companyId);
  boolean existsById(Integer companyId);
}