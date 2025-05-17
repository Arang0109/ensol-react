package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.common.util.ValidationUtils;
import com.ensolution.ensol.dto.request.ScheduleRegisterRequestDto;
import com.ensolution.ensol.dto.app.query.table.ScheduledWorkplaceTableDto;
import com.ensolution.ensol.dto.response.ScheduledStackResponseDto;
import com.ensolution.ensol.service.schedule.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/api/management/schedules")
@RequiredArgsConstructor
public class ScheduleController {
  private final ScheduleService scheduleService;
  
  @Operation(
      summary = "일정 목록 조회(사업장) - 대기자가측정",
      description = "등록된 모든 일정 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiResponseMessage.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping("/atmosphere")
  public ResponseEntity<ApiResponseMessage<List<ScheduledWorkplaceTableDto>>> getScheduledWorkplaceList() {
    
    return ResponseEntity.ok(new ApiResponseMessage<>(true, "조회 성공", scheduleService.findAllSchedule()));
  }
  
  @Operation(
      summary = "일정 목록 조회(시설) - 대기자가측정",
      description = "등록된 모든 일정 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiResponseMessage.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping("/atmosphere/{scheduledWorkplaceId}")
  public ResponseEntity<ApiResponseMessage<ScheduledStackResponseDto>> getScheduledStackList(
      @PathVariable Integer scheduledWorkplaceId
  ) {
    
    return ResponseEntity.ok(new ApiResponseMessage<>(true, "조회 성공", scheduleService.findSubSchedules(scheduledWorkplaceId)));
  }
  
  @Operation(summary = "측정 일정 등록", description = "새로운 측정 일정을 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping
  public ResponseEntity<ApiResponseMessage<ScheduleRegisterRequestDto>> createSchedule(
      @Valid @RequestBody ScheduleRegisterRequestDto scheduleRegisterRequest,
      BindingResult bindingResult) {
    
    if (bindingResult.hasErrors()) { return ValidationUtils.handleBindingErrors(bindingResult); }
    
//    System.out.println(scheduleRegisterRequest);
    scheduleService.registerSchedule(scheduleRegisterRequest);
    ApiResponseMessage<ScheduleRegisterRequestDto> success = new ApiResponseMessage<>(true, "일정 등록 성공", null);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(success);
  }
}
