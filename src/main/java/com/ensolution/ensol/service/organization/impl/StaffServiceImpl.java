package com.ensolution.ensol.service.organization.impl;

import com.ensolution.ensol.dto.app.entity.oranization.StaffDto;
import com.ensolution.ensol.service.organization.StaffDataService;
import com.ensolution.ensol.service.organization.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
  private final StaffDataService staffDataService;
  
  @Override
  public List<StaffDto> findAllStaffs() {
    return staffDataService.findAll();
  }
}
