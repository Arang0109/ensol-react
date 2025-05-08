package com.ensolution.ensol.service.company;

import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;

import java.util.List;
import java.util.Optional;

public interface WorkplaceDataService {
  Optional<WorkplaceDto> findWorkplaceById(Integer id);
  List<WorkplaceDto> findWorkplacesByCompanyId(Integer companyId);
  List<WorkplaceDto> findAll();
  Integer findFactoryId(Integer id);
  void saveWorkplace(WorkplaceDto workplaceDto);
  void updateWorkplace(WorkplaceDto workplaceDto);
  void deleteWorkplace(Integer workplaceId);
  void deleteWorkplaces(List<Integer> ids);
  boolean existsById(Integer id);
}
