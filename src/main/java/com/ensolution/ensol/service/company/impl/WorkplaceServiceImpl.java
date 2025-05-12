package com.ensolution.ensol.service.company.impl;

import com.ensolution.ensol.common.exception.CustomDKException;
import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import com.ensolution.ensol.dto.request.WorkplaceUpdateRequestDto;
import com.ensolution.ensol.service.company.WorkplaceDataService;
import com.ensolution.ensol.service.company.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkplaceServiceImpl implements WorkplaceService {
  private final WorkplaceDataService workplaceDataService;

  @Override
  public Optional<WorkplaceDto> getWorkplaceById(Integer workplaceId) {
    return workplaceDataService.getWorkplaceById(workplaceId);
  }

  @Override
  public List<WorkplaceDto> findWorkplacesByCompanyId(Integer companyId) {
    return workplaceDataService.findWorkplacesByCompanyId(companyId);
  }

  @Override
  public List<WorkplaceDto> findAllWorkplaces() {
    return workplaceDataService.findAllWorkplaces();
  }

  @Override
  public WorkplaceDto registerWorkplace(WorkplaceDto workplaceDto) {
    try {
      workplaceDto.setRegDate(LocalDate.now());
      workplaceDataService.saveWorkplace(workplaceDto);
    } catch (DuplicateKeyException e) {
      throw new CustomDKException("workplace", "Name", workplaceDto.getWorkplaceName(), e);
    }
    
    return workplaceDto;
  }

  @Override
  public void updateWorkplace(Integer workplaceId, WorkplaceUpdateRequestDto requestDto) {
    if (!workplaceDataService.existsById(workplaceId)) {
      throw new IllegalArgumentException("Workplace with Name " + requestDto.getWorkplaceName() + " does not exist.");
    }
    
    requestDto.setWorkplaceId(workplaceId);
    workplaceDataService.updateWorkplaceProfile(requestDto);
  }

  @Override
  public void deleteWorkplace(Integer workplaceId) {
    try {
      workplaceDataService.deleteWorkplace(workplaceId);
    } catch (DataAccessException e) {
      throw new RuntimeException("Database error occurred while deleting companies", e);
    }
  }
}