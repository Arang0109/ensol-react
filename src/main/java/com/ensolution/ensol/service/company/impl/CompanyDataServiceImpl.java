package com.ensolution.ensol.service.company.impl;

import static com.ensolution.ensol.common.util.UpdateUtils.updateIfPresent;

import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.dto.request.CompanyUpdateRequestDto;
import com.ensolution.ensol.entity.app.facility.Company;
import com.ensolution.ensol.mapper.app.facility.CompanyMapper;
import com.ensolution.ensol.repository.app.jpa.facility.CompanyRepository;
import com.ensolution.ensol.service.company.CompanyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyDataServiceImpl implements CompanyDataService {
  private final CompanyRepository companyRepository;
  private final CompanyMapper companyMapper;

  @Override
  public Optional<CompanyDto> getCompanyById(Integer companyId) {
    return companyRepository.findById(companyId).map(companyMapper::toDto);
  }

  @Override
  public List<CompanyDto> findAllCompanies() {
    List<Company> companies = companyRepository.findAll();
    return companyMapper.toDtoList(companies);
  }

  @Override
  @Transactional
  public void saveCompany(CompanyDto companyDto) {
    companyRepository.save(companyMapper.toEntity(companyDto));
  }

  @Override
  @Transactional
  public void updateCompanyProfile(CompanyUpdateRequestDto requestDto) {
    Company company = companyRepository.findById(requestDto.getCompanyId())
        .orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 회사 ID: " + requestDto.getCompanyId()));
    
    updateIfPresent(requestDto.getCompanyName(), company::setCompanyName);
    updateIfPresent(requestDto.getAddress(), company::setAddress);
    updateIfPresent(requestDto.getBizNumber(), company::setBizNumber);
    updateIfPresent(requestDto.getCeoName(), company::setCeoName);
  }

  @Override
  @Transactional
  public void deleteCompany(Integer companyId) {
    if (!companyRepository.existsById(companyId)) {
      throw new IllegalArgumentException("삭제할 회사가 존재하지 않습니다. ID: " + companyId);
    }
    companyRepository.deleteById(companyId);
  }

  @Override
  public boolean existsById(Integer id) {
    return companyRepository.existsById(id);
  }
}