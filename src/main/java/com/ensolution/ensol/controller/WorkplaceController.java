package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.dto.response.WorkplaceDetailResponseDto;
import com.ensolution.ensol.common.util.ValidationUtils;
import com.ensolution.ensol.dto.app.entity.facility.WorkplaceDto;
import com.ensolution.ensol.service.company.WorkplaceService;
import com.ensolution.ensol.service.stack.StackService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/management/workplaces")
@RequiredArgsConstructor
public class WorkplaceController {

  private final WorkplaceService workplaceService;
  private final StackService stackService;

  @Operation(
      summary = "측정대상 사업장 목록 조회",
      description = "등록된 모든 사업장 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = WorkplaceDto.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping()
  /*  측정대상 사업장 목록 조희 */
  public List<WorkplaceDto> getWorkplaceList() {

    return workplaceService.findAllWorkplaces();

  }

  @Operation(summary = "측정대상 사업장 상세페이지 조회", description = "사업장 ID로 상세 정보를 조회합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공",
          content = @Content(schema = @Schema(implementation = WorkplaceDetailResponseDto.class))),
      @ApiResponse(responseCode = "404", description = "요청 페이지 없음")
  })
  @GetMapping("/{workplaceId}")
  public ResponseEntity<ApiResponseMessage<WorkplaceDetailResponseDto>> getWorkplaceDetail(
      @PathVariable Integer workplaceId
  ) {
    Optional<WorkplaceDto> workplace = workplaceService.findWorkplaceById(workplaceId);

    if (workplace.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponseMessage<>(false, "조회 실패", null));
    }

    WorkplaceDetailResponseDto responseDto = new WorkplaceDetailResponseDto();
    responseDto.setWorkplace(workplace.get());
    responseDto.setStacks(stackService.findStacksByWorkplaceId(workplaceId));

    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", responseDto)
    );
  }

  @Operation(summary = "측정대상 사업장 등록", description = "새로운 사업장을 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping()
  public ResponseEntity<ApiResponseMessage<WorkplaceDto>> createWorkplace(
      @Valid @RequestBody WorkplaceDto workplaceDto,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }

    workplaceService.createWorkplace(workplaceDto);
    ApiResponseMessage<WorkplaceDto> success = new ApiResponseMessage<>(true, "등록 성공", null);
    return ResponseEntity.status(HttpStatus.CREATED).body(success);
  }

  @Operation(
      summary = "측정대상 사업장 정보 수정",
      description = "사업장 정보를 수정합니다.")
  @PatchMapping("/{workplaceId}")
  public ResponseEntity<String> updateWorkplace(
      @PathVariable Integer workplaceId,
      @Valid @RequestBody WorkplaceDto workplaceDto
  ) {
    workplaceDto.setWorkplaceId(workplaceId);
    workplaceService.updateWorkplace(workplaceDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "측정대상 사업장 데이터 삭제")
  @DeleteMapping("/{workplaceId}")
  public ResponseEntity<String> deleteWorkplace(
      @PathVariable Integer workplaceId
  ) {
    workplaceService.removeWorkplace(workplaceId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
