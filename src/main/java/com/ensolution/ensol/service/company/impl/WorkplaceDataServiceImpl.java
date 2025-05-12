package com.ensolution.ensol.service.company.impl;

import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import com.ensolution.ensol.dto.request.WorkplaceUpdateRequestDto;
import com.ensolution.ensol.entity.app.facility.Workplace;
import com.ensolution.ensol.mapper.app.facility.WorkplaceMapper;
import com.ensolution.ensol.repository.app.jpa.facility.WorkplaceRepository;
import com.ensolution.ensol.service.company.WorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.ensolution.ensol.common.util.UpdateUtils.updateIfPresent;

@Service
@RequiredArgsConstructor
public class WorkplaceDataServiceImpl implements WorkplaceDataService {
  private final WorkplaceRepository workplaceRepository;
  private final WorkplaceMapper workplaceMapper;

  @Override
  public Optional<WorkplaceDto> getWorkplaceById(Integer workplaceId) {
    return workplaceRepository.findById(workplaceId).map(workplaceMapper::toDto);
  }

  @Override
  public List<WorkplaceDto> findWorkplacesByCompanyId(Integer companyId) {
    List<Workplace> workplaces = workplaceRepository.findByCompany_CompanyId(companyId);
    return workplaceMapper.toDtoList(workplaces);
  }

  @Override
  public List<WorkplaceDto> findAllWorkplaces() {
    List<Workplace> workplaces = workplaceRepository.findAll();
    return workplaceMapper.toDtoList(workplaces);
  }

  @Override
  @Transactional
  public void saveWorkplace(WorkplaceDto workplaceDto) {
    Workplace workplace = workplaceMapper.toEntity(workplaceDto);
    workplaceRepository.save(workplace);
  }

  @Override
  @Transactional
  public void updateWorkplaceProfile(WorkplaceUpdateRequestDto requestDto) {
    Workplace workplace = workplaceRepository.findById(requestDto.getWorkplaceId())
        .orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 사업장 ID: " + requestDto.getWorkplaceId()));

    updateIfPresent(requestDto.getWorkplaceName(), workplace::setWorkplaceName);
    updateIfPresent(requestDto.getAddress(), workplace::setAddress);
    updateIfPresent(requestDto.getBusinessType(), workplace::setBusinessType);
    updateIfPresent(requestDto.getMainProduction(), workplace::setMainProduction);
    updateIfPresent(requestDto.getWorkplaceSize(), workplace::setWorkplaceSize);
  }

  @Override
  public void deleteWorkplace(Integer workplaceId) { workplaceRepository.deleteById(workplaceId); }

  @Override
  public boolean existsById(Integer workplaceId) {
    return workplaceRepository.existsById(workplaceId);
  }
}