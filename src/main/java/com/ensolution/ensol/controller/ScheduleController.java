package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.common.util.ValidationUtils;
import com.ensolution.ensol.dto.app.entity.schedule.ScheduleDto;
import com.ensolution.ensol.dto.app.query.table.ScheduleTableDto;
import com.ensolution.ensol.dto.app.query.table.SubScheduleTableDto;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/management/schedules")
@RequiredArgsConstructor
public class ScheduleController {
  private final ScheduleService scheduleService;
  
  @Operation(
      summary = "일정 목록 조회(주번호 기준)",
      description = "등록된 모든 일정 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ScheduleTableDto.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping
  public List<ScheduleTableDto> getScheduleList() {
    return scheduleService.findAllSchedule();
  }
  
  @Operation(
      summary = "일정 목록 조회(부번호 기준)",
      description = "등록된 모든 일정 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = SubScheduleTableDto.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping("/subSchedules")
  public List<SubScheduleTableDto> getSubScheduleList(
      @RequestParam("ids") List<Integer> groupedScheduleIds
  ) {
    return scheduleService.findSubSchedules(groupedScheduleIds);
  }
  
  @Operation(summary = "측정 일정 등록", description = "새로운 측정 일정을 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping
  public ResponseEntity<ApiResponseMessage<ScheduleDto>> createSchedule(
      @Valid @RequestBody ScheduleDto schedule,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) { return ValidationUtils.handleBindingErrors(bindingResult); }

    ScheduleDto createdSchedule = scheduleService.createSchedule(
        schedule.getGroupedSchedule(), schedule.getScheduledMeasurements()
    );
    ApiResponseMessage<ScheduleDto> success = new ApiResponseMessage<>(true, "일정 등록 성공", createdSchedule);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(success);
  }
}
