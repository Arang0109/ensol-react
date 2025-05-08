package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.common.util.ValidationUtils;
import com.ensolution.ensol.dto.response.StackDetailResponseDto;
import com.ensolution.ensol.dto.app.entity.facility.StackDto;
import com.ensolution.ensol.service.stack.StackMeasurementService;
import com.ensolution.ensol.service.stack.StackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/management/stacks")
@RequiredArgsConstructor
public class StackController {

  private final StackService stackService;
  private final StackMeasurementService stackMeasurementService;

  @Operation(
      summary = "배출시설 목록 조회",
      description = "등록된 모든 시설 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StackDto.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping()
  /*  측정대상 사업장 목록 조희 */
  public List<StackDto> getStackList() { return stackService.findAllStacks(); }

  @Operation(summary = "배출시설 상세페이지 조회")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "조회 성공",
                  content = @Content(schema = @Schema(implementation = StackDetailResponseDto.class))),
          @ApiResponse(responseCode = "404", description = "요청 페이지 없음")
  })
  @GetMapping("/{stackId}")
  public ResponseEntity<ApiResponseMessage<StackDetailResponseDto>> getStackDetail(
          @PathVariable Integer stackId
  ) {
    Optional<StackDto> stack = stackService.findStackById(stackId);

    if (stack.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(new ApiResponseMessage<>(false, "조회 실패", null));
    }

    StackDetailResponseDto responseDto = new StackDetailResponseDto();
    responseDto.setStack(stack.get());
    responseDto.setStackMeasurements(stackMeasurementService.findStackMeasurementsByStackId(stackId));
    
    return ResponseEntity.ok(
            new ApiResponseMessage<>(true, "조회 성공", responseDto)
    );
  }
  
  @Operation(summary = "배출시설 등록", description = "새로운 배출시설를 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping()
  public ResponseEntity<ApiResponseMessage<StackDto>> createStack(
      @Valid @RequestBody StackDto stackDto,
      BindingResult bindingResult) {
    
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    
    StackDto createStack = stackService.createStack(stackDto);
    ApiResponseMessage<StackDto> success = new ApiResponseMessage<>(true, "등록 성공", createStack);
    return ResponseEntity.status(HttpStatus.CREATED).body(success);
  }
  
  @Operation(summary = "배출시설 정보 수정")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "수정 성공",
          content = @Content(schema = @Schema(implementation = StackDetailResponseDto.class))),
      @ApiResponse(responseCode = "404", description = "요청 페이지 없음")
  })
  @PatchMapping("/{stackId}")
  public ResponseEntity<String> updateStack(
          @PathVariable Integer stackId,
          @Valid @RequestBody StackDto stackDto
  ) {
    stackDto.setStackId(stackId);
    stackService.updateStack(stackDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  
  @Operation(summary = "배출시설 데이터 삭제")
  @DeleteMapping("/{stackId}")
  public ResponseEntity<String> deleteStack(
      @PathVariable Integer stackId
  ) {
    stackService.removeStack(stackId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
