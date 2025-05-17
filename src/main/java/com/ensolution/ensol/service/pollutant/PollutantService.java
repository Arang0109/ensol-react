package com.ensolution.ensol.service.pollutant;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;

import java.util.List;
import java.util.Optional;

public interface PollutantService {
  Optional<PollutantDto> getPollutantById(Integer pollutantId);
  List<PollutantDto> findAllPollutants();
}