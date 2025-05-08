package com.ensolution.ensol.service.document.impl;

import com.ensolution.ensol.common.util.CapabilityScoreCalculator;
import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;
import com.ensolution.ensol.service.document.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {
  @Override
  public Map<String, String> CapabilityScorer(List<PollutantDto> pollutants) {
   CapabilityScoreCalculator calculator = new CapabilityScoreCalculator(pollutants);
   return calculator.getScore();
  }
}
