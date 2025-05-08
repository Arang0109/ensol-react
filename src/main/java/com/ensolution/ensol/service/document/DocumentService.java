package com.ensolution.ensol.service.document;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;

import java.util.List;

public interface DocumentService {
  List<PollutantDto> selectPollutlantsList(List<PollutantDto> pollutantDto);
}
