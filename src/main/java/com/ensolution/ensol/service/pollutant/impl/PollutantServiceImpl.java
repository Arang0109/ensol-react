package com.ensolution.ensol.service.pollutant.impl;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;
import com.ensolution.ensol.service.pollutant.PollutantDataService;
import com.ensolution.ensol.service.pollutant.PollutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollutantServiceImpl implements PollutantService {
  private final PollutantDataService pollutantDataService;

  @Override
  public PollutantDto findPollutantById(Integer pollutantId) {
    return pollutantDataService.findPollutantById(pollutantId);
  }

  @Override
  public List<PollutantDto> findAllPollutants() {
    return pollutantDataService.findAllPollutants();
  }
}
