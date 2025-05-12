package com.ensolution.ensol.mapper.app.facility;

import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.entity.app.facility.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
  
  CompanyDto toDto(Company company);
  
  @Mapping(target = "companyId", ignore = true)
  Company toEntity(CompanyDto companyDto);
  
  List<CompanyDto> toDtoList(List<Company> companies);
}