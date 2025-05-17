package com.ensolution.ensol.service.pollutant.impl;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;
import com.ensolution.ensol.entity.app.pollutant.Pollutant;
import com.ensolution.ensol.mapper.app.pollutant.PollutantMapper;
import com.ensolution.ensol.repository.app.jpa.pollutant.PollutantRepository;
import com.ensolution.ensol.service.pollutant.PollutantDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollutantDataServiceImpl implements PollutantDataService {
  private final PollutantRepository pollutantRepository;
  private final PollutantMapper pollutantMapper;

  @Override
  public Optional<PollutantDto> getPollutantById(Integer pollutantId) {
    return pollutantRepository.findById(pollutantId).map(pollutantMapper::toDto);
  }

  @Override
  public List<PollutantDto> findAllPollutants() {
    List<Pollutant> pollutants = pollutantRepository.findAll();
    return pollutantMapper.toDtoList(pollutants);
  }
}