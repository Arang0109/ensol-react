package com.ensolution.ensol.service.organization.impl;

import com.ensolution.ensol.dto.app.entity.oranization.StaffDto;
import com.ensolution.ensol.entity.app.organization.Staff;
import com.ensolution.ensol.mapper.app.organization.StaffMapper;
import com.ensolution.ensol.repository.app.jpa.organization.StaffRepository;
import com.ensolution.ensol.service.organization.StaffDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffDataServiceImpl implements StaffDataService {
  private final StaffRepository staffRepository;
  private final StaffMapper staffMapper;
  
  @Override
  public List<StaffDto> findAll() {
    List<Staff> staffs = staffRepository.findAll();
    return staffMapper.toDtoList(staffs);
  }
}
