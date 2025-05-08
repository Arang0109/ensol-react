package com.ensolution.ensol.service.company;

import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Optional<CompanyDto> findCompanyById(Integer id);
    List<CompanyDto> findAllCompanies();
    CompanyDto createCompany(CompanyDto companyDto);
    void updateCompany(CompanyDto companyDto);
    void removeCompanies(List<CompanyDto> companies);
    void removeCompany(Integer companyId);
}
