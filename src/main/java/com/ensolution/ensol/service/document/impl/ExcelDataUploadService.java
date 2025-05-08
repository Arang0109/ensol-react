package com.ensolution.ensol.service.document.impl;

import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.dto.app.query.ExcelStackMeasurementDto;
import com.ensolution.ensol.entity.app.pollutant.Pollutant;
import com.ensolution.ensol.entity.app.facility.Stack;
import com.ensolution.ensol.mapper.app.pollutant.PollutantMapper;
import com.ensolution.ensol.mapper.app.facility.StackMapper;
import com.ensolution.ensol.mapper.app.facility.StackMeasurementMapper;
import com.ensolution.ensol.repository.app.jpa.pollutant.PollutantRepository;
import com.ensolution.ensol.repository.app.jpa.facility.StackMeasurementRepository;
import com.ensolution.ensol.repository.app.jpa.facility.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExcelDataUploadService {
  StackMapper stackMapper;
  StackRepository stackRepository;
  PollutantMapper pollutantMapper;
  PollutantRepository pollutantRepository;
  StackMeasurementMapper stackMeasurementMapper;
  StackMeasurementRepository stackMeasurementRepository;

  public void addStackMeasurement(Integer workplaceId, List<ExcelStackMeasurementDto> excelData) {
    for (ExcelStackMeasurementDto item : excelData) {
      item.setWorkplace_id(workplaceId);

      StackMeasurementDto stackMeasurementDto = new StackMeasurementDto();

      Optional<Stack> stack = stackRepository.findByStackName(item.getStack_name());
      Optional<Pollutant> pollutant = pollutantRepository.findByPollutantNameKR(item.getPollutant_name());
      if (stack.isPresent() && pollutant.isPresent()) {
        stackMeasurementDto.setStackId(stackMapper.toDto(stack.get()).getStackId());
        stackMeasurementDto.setPollutantId(pollutantMapper.toDto(pollutant.get()).getPollutantId());
        stackMeasurementDto.setCycleType(item.getCycle_type());
        stackMeasurementDto.setMeasured(!item.getCycle_type().equals("nomeasure"));
        stackMeasurementDto.setAllowValue(item.getAllow_value());
      }
      ;
      stackMeasurementRepository.save(stackMeasurementMapper.toEntity(stackMeasurementDto));
    }
  }
}
