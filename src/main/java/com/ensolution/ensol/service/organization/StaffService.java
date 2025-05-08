package com.ensolution.ensol.service.organization;

import com.ensolution.ensol.dto.app.entity.oranization.StaffDto;

import java.util.List;

public interface StaffService {
  List<StaffDto> findAllStaffs();
}
