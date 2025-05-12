package com.ensolution.ensol.service.company;

import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.dto.request.CompanyUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Optional<CompanyDto> getCompanyById(Integer companyId);
    List<CompanyDto> findAllCompanies();
    CompanyDto registerCompany(CompanyDto companyDto);
    void updateCompanyProfile(Integer comapnyId, CompanyUpdateRequestDto requestDto);
    void deleteCompany(Integer companyId);
}