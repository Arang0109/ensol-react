package com.ensolution.ensol.service.company.impl;

import com.ensolution.ensol.common.exception.CustomDKException;
import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.dto.request.CompanyUpdateRequestDto;
import com.ensolution.ensol.service.company.CompanyDataService;
import com.ensolution.ensol.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
  private final CompanyDataService companyDataService;

  @Override
  public Optional<CompanyDto> getCompanyById(Integer companyId) {
    return companyDataService.getCompanyById(companyId);
  }

  @Override
  public List<CompanyDto> findAllCompanies() {
    return companyDataService.findAllCompanies();
  }

  @Override
  public CompanyDto registerCompany(CompanyDto companyDto) {
    try {
      companyDataService.saveCompany(companyDto);
    } catch (DuplicateKeyException e) {
      throw new CustomDKException("company", "Name", companyDto.getCompanyName(), e);
    }
    return companyDto;
  }

  @Override
  public void updateCompanyProfile(Integer companyId, CompanyUpdateRequestDto requestDto) {
    if (!companyDataService.existsById(companyId)) {
      throw new IllegalArgumentException("Company with Name " + requestDto.getCompanyName() + " does not exist.");
    }
    
    requestDto.setCompanyId(companyId);
    companyDataService.updateCompanyProfile(requestDto);
  }

  @Override
  public void deleteCompany(Integer companyId) {
    try {
      companyDataService.deleteCompany(companyId);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while deleting companies", e);
    }
  }
}