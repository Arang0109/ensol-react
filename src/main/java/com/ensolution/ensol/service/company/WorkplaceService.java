package com.ensolution.ensol.service.company;

import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import com.ensolution.ensol.dto.request.WorkplaceUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface WorkplaceService {
  Optional<WorkplaceDto> getWorkplaceById(Integer WorkplaceId);
  List<WorkplaceDto> findWorkplacesByCompanyId(Integer WorkplaceId);
  List<WorkplaceDto> findAllWorkplaces();
  WorkplaceDto registerWorkplace(WorkplaceDto workplaceDto);
  void updateWorkplace(Integer workplaceId, WorkplaceUpdateRequestDto requestDto);
  void deleteWorkplace(Integer workplaceId);
}