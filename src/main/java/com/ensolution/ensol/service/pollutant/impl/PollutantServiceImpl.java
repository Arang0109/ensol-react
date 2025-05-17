package com.ensolution.ensol.service.pollutant.impl;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;
import com.ensolution.ensol.service.pollutant.PollutantDataService;
import com.ensolution.ensol.service.pollutant.PollutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollutantServiceImpl implements PollutantService {
  private final PollutantDataService pollutantDataService;

  @Override
  public Optional<PollutantDto> getPollutantById(Integer pollutantId) {
    return pollutantDataService.getPollutantById(pollutantId);
  }

  @Override
  public List<PollutantDto> findAllPollutants() {
    return pollutantDataService.findAllPollutants();
  }
}