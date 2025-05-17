package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;
import com.ensolution.ensol.service.pollutant.PollutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/management/pollutants")
@RequiredArgsConstructor
public class PollutantController {

  private final PollutantService pollutantService;

  @Operation(
      summary = "배출오염물질 목록 조회",
      description = "등록된 모든 오염물질 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiResponseMessage.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping
  /*  배출오염물질 목록 조희 */
  public ResponseEntity<ApiResponseMessage<List<PollutantDto>>> getPollutantList() {
    
    List<PollutantDto> list = pollutantService.findAllPollutants();

    return ResponseEntity.ok(new ApiResponseMessage<>(true, "조회 성공", list));

  }
}