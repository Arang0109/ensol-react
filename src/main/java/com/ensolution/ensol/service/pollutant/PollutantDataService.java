package com.ensolution.ensol.service.pollutant;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;

import java.util.List;

public interface PollutantDataService {
  PollutantDto findPollutantById(Integer pollutantId);
  
  List<PollutantDto> findAllPollutants();
}
