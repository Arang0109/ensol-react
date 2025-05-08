package com.ensolution.ensol.service.pollutant.impl;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;
import com.ensolution.ensol.entity.app.pollutant.Pollutant;
import com.ensolution.ensol.mapper.app.pollutant.PollutantMapper;
import com.ensolution.ensol.repository.app.jpa.pollutant.PollutantRepository;
import com.ensolution.ensol.service.pollutant.PollutantDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollutantDataServiceImpl implements PollutantDataService {
  private final PollutantRepository pollutantRepository;
  private final PollutantMapper pollutantMapper;

  @Override
  public PollutantDto findPollutantById(Integer pollutantId) {
    Pollutant pollutant = pollutantRepository.findById(pollutantId)
        .orElseThrow(() -> new RuntimeException("pollutant not found"));

    return pollutantMapper.toDto(pollutant);
  }

  @Override
  public List<PollutantDto> findAllPollutants() {
    return pollutantMapper.toDtoList(pollutantRepository.findAll());
  }
}
