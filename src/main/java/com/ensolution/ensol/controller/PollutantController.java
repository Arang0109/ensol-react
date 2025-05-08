package com.ensolution.ensol.controller;

import com.ensolution.ensol.dto.app.entity.pollutant.PollutantDto;
import com.ensolution.ensol.service.pollutant.PollutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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
      summary = "배출 오염 물질 목록 조회",
      description = "등록된 모든 오염 물질 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = PollutantDto.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping()
  /*  측정대상 사업장 목록 조희 */
  public List<PollutantDto> getPollutantList() {

    return pollutantService.findAllPollutants();

  }
}
