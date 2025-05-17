package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.common.util.ValidationUtils;
import com.ensolution.ensol.dto.app.entity.facility.StackMeasurementDto;
import com.ensolution.ensol.service.stack.StackMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  
  @Operation(summary = "배출시설 측정항목 등록", description = "새로운 측정항목을 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping()
  public ResponseEntity<ApiResponseMessage<StackMeasurementDto>> registerStackMeasurement(
      @Valid @RequestBody StackMeasurementDto stackMeasurement,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    StackMeasurementDto createdStackMeasurement = stackMeasurementService.registerStackMeasurement(stackMeasurement);
    ApiResponseMessage<StackMeasurementDto> success = new ApiResponseMessage<>(true, "등록 성공", createdStackMeasurement);
    return ResponseEntity.status(HttpStatus.CREATED).body(success);
  }
  
  @Operation(summary = "배출 시설 측정 항목 삭제")
  @DeleteMapping
  public ResponseEntity<String> deleteStackMeasurements(
      
      @RequestBody List<Integer> stackMeasurementIds
      
  ) {
    stackMeasurementService.deleteStackMeasurements(stackMeasurementIds);
    return ResponseEntity.noContent().build();
  }
}
