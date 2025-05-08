package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.common.util.ValidationUtils;
import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.service.stack.StackMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/management/stack-measurements")
@RequiredArgsConstructor
public class StackMeasurementController {

  private final StackMeasurementService stackMeasurementService;
  
  @Operation(summary = "배출시설 측정항목 추가")
  @PostMapping()
  public ResponseEntity<ApiResponseMessage<StackMeasurementDto>> saveStackMeasurement(
      @Valid @RequestBody StackMeasurementDto stackMeasurement,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    StackMeasurementDto createdStackMeasurement = stackMeasurementService.createStackMeasurement(stackMeasurement);
    ApiResponseMessage<StackMeasurementDto> success = new ApiResponseMessage<>(true, "등록 성공", createdStackMeasurement);
    return ResponseEntity.status(HttpStatus.CREATED).body(success);
  }
  
  @Operation(summary = "배출 시설 측정 항목 삭제")
  @DeleteMapping()
  public ResponseEntity<String> removeStackMeasurements(
      @RequestBody List<Integer> stackMeasurementIds
  ) {
    stackMeasurementService.removeStackMeasurements(stackMeasurementIds);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
