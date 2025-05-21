package com.ensolution.ensol.common.validator;

import com.ensolution.ensol.common.util.ValidationPreprocessor;
import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.repository.app.jpa.facility.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CompanyValidator implements Validator {
  private final CompanyRepository companyRepository;
  
  @Override
  public boolean supports(@NonNull Class<?> clazz) {
    return CompanyDto.class.isAssignableFrom(clazz);
  }
  
  @Override
  public void validate(@NonNull Object target, @NonNull Errors errors) {
    CompanyDto company = (CompanyDto) target;
    
    String param = company.getCompanyName();
    if (param != null) {
      String cleanParam = ValidationPreprocessor.toLowerTrimmed(
          ValidationPreprocessor.normalizeInput(param)
      );
      
      if (companyRepository.existsByCompanyNormalizedName(cleanParam)) {
        errors.rejectValue("companyName", "duplicate.companyName", "이미 등록된 회사입니다.");
      }
      
    }
    
    if (company.getBizNumber() != null && companyRepository.existsByBizNumber((company.getBizNumber()))) {
      errors.rejectValue("bizNumber", "duplicate.bizNumber", "이미 등록된 사업자 등록번호입니다.");
    }
  }
}
