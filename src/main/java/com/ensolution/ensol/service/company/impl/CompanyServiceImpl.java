package com.ensolution.ensol.service.company.impl;

import com.ensolution.ensol.common.exception.CustomDKException;
import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.service.company.CompanyDataService;
import com.ensolution.ensol.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
  private final CompanyDataService companyDataService;

  @Override
  public Optional<CompanyDto> findCompanyById(Integer companyId) {
    return companyDataService.findCompanyById(companyId);
  }

  @Override
  public List<CompanyDto> findAllCompanies() {
    return companyDataService.findAll();
  }

  @Override
  public CompanyDto createCompany(CompanyDto companyDto) {
    try {
      companyDto.setRegDate(LocalDate.now());
      companyDataService.saveCompany(companyDto);
    } catch (DuplicateKeyException e) {
      throw new CustomDKException("company", "Name", companyDto.getCompanyName(), e);
    }
    return companyDto;
  }

  @Override
  public void updateCompany(CompanyDto companyDto) {
    if (!companyDataService.existsById(companyDto.getCompanyId())) {
      throw new IllegalArgumentException("Company with Name " + companyDto.getCompanyName() + " does not exist.");
    }
    companyDataService.updateCompany(companyDto);
  }

  @Override
  public void removeCompanies(List<CompanyDto> companies) {
    if (companies == null || companies.isEmpty()) {
      throw new IllegalArgumentException("Input list cannot be null or empty");
    }

    List<Integer> ids = new ArrayList<>();

    for (CompanyDto companyDto : companies) {
      if (companyDto == null) {
        throw new IllegalArgumentException("CompanyDto cannot be null");
      }
      ids.add(companyDto.getCompanyId());
    }

    try {
      companyDataService.deleteCompanies(ids);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while deleting companies", e);
    }
  }

  @Override
  public void removeCompany(Integer companyId) {
    try {
      companyDataService.deleteCompany(companyId);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while deleting companies", e);
    }
  }
}
