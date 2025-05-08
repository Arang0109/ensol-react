package com.ensolution.ensol.service.company.impl;

import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import com.ensolution.ensol.entity.app.facility.Workplace;
import com.ensolution.ensol.mapper.app.facility.WorkplaceMapper;
import com.ensolution.ensol.repository.app.jpa.facility.CompanyRepository;
import com.ensolution.ensol.repository.app.jpa.facility.WorkplaceRepository;
import com.ensolution.ensol.service.company.WorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkplaceDataServiceImpl implements WorkplaceDataService {
  private final CompanyRepository companyRepository;
  private final WorkplaceRepository workplaceRepository;
  private final WorkplaceMapper workplaceMapper;

  @Override
  public Optional<WorkplaceDto> findWorkplaceById(Integer id) {
    return workplaceRepository.findById(id).map(workplaceMapper::toDto);
  }

  @Override
  public List<WorkplaceDto> findWorkplacesByCompanyId(Integer companyId) {
    List<Workplace> workplaces = workplaceRepository.findByCompany_CompanyId(companyId);
    return workplaceMapper.toDtoList(workplaces);
  }

  @Override
  public List<WorkplaceDto> findAll() {
    List<Workplace> workplaces = workplaceRepository.findAll();
    return workplaceMapper.toDtoList(workplaces);
  }

  @Override
  public Integer findFactoryId(Integer workplaceId) {


    return 0;
  }

  @Override
  @Transactional
  public void saveWorkplace(WorkplaceDto workplaceDto) {
    Workplace workplace = workplaceMapper.toEntity(workplaceDto);
    workplaceRepository.save(workplace);
  }

  @Override
  @Transactional
  public void updateWorkplace(WorkplaceDto workplaceDto) {
    Workplace workplace = workplaceRepository.findById(workplaceDto.getWorkplaceId())
        .orElseThrow(() -> new RuntimeException("Workplace not found"));

    if (workplaceDto.getWorkplaceName() != null) {
      workplace.setWorkplaceName(workplaceDto.getWorkplaceName());
    }

    if (workplaceDto.getAddress() != null) {
      workplace.setAddress(workplaceDto.getAddress());
    }

    workplace.setBusinessType(workplaceDto.getBusinessType());
    workplace.setMainProduction(workplaceDto.getMainProduction());
    workplace.setWorkplaceSize(workplaceDto.getWorkplaceSize());
  }

  @Override
  public void deleteWorkplaces(List<Integer> ids) {
    workplaceRepository.deleteAllById(ids);
  }

  @Override
  public void deleteWorkplace(Integer workplaceId) { workplaceRepository.deleteById(workplaceId); }

  @Override
  public boolean existsById(Integer id) {
    return workplaceRepository.existsById(id);
  }
}
