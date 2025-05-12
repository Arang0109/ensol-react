package com.ensolution.ensol.service.company;

import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import com.ensolution.ensol.dto.request.WorkplaceUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface WorkplaceDataService {
  Optional<WorkplaceDto> getWorkplaceById(Integer workplaceId);
  List<WorkplaceDto> findWorkplacesByCompanyId(Integer companyId);
  List<WorkplaceDto> findAllWorkplaces();
  void saveWorkplace(WorkplaceDto workplaceDto);
  void updateWorkplaceProfile(WorkplaceUpdateRequestDto requestDto);
  void deleteWorkplace(Integer workplaceId);
  boolean existsById(Integer workplaceId);
}