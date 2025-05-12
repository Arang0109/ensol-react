package com.ensolution.ensol.controller;

import com.ensolution.ensol.common.util.ApiResponseMessage;
import com.ensolution.ensol.dto.request.CompanyUpdateRequestDto;
import com.ensolution.ensol.dto.response.CompanyDetailResponseDto;
import com.ensolution.ensol.common.util.ValidationUtils;
import com.ensolution.ensol.dto.app.entity.facility.CompanyDto;
import com.ensolution.ensol.service.company.CompanyService;
import com.ensolution.ensol.service.company.WorkplaceService;
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
@RequestMapping("/api/management/companies")
@RequiredArgsConstructor
public class CompanyController {

  private final CompanyService companyService;
  private final WorkplaceService workplaceService;

  @Operation(
      summary = "측정대행 의뢰업체 목록 조회",
      description = "등록된 모든 의뢰업체 정보를 조회합니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "정상 응답",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiResponseMessage.class))),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping
  /*  측정대행 의뢰업체 목록 조회 */
  public ResponseEntity<ApiResponseMessage<List<CompanyDto>>> getCompanyList() {
    
    List<CompanyDto> list = companyService.findAllCompanies();
    
    return ResponseEntity.ok(new ApiResponseMessage<>(true, "조회 성공", list));
  }

  @Operation(summary = "측정대행 의뢰업체 등록", description = "새로운 의뢰업체를 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "의뢰업체 등록 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping
  public ResponseEntity<ApiResponseMessage<CompanyDto>> registerCompany(
      
      @Valid @RequestBody CompanyDto companyDto,
      BindingResult bindingResult
      
  ) {

    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }

    CompanyDto register = companyService.registerCompany(companyDto);
    ApiResponseMessage<CompanyDto> success = new ApiResponseMessage<>(true, "업체 등록에 성공했습니다.", register);
    return ResponseEntity.status(HttpStatus.CREATED).body(success);
  }

  @Operation(summary = "측정대행 의뢰업체 상세페이지 조회", description = "업체 ID로 상세 정보를 조회합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "의뢰업체 조회 성공",
          content = @Content(schema = @Schema(implementation = ApiResponseMessage.class))),
      @ApiResponse(responseCode = "404", description = "요청 페이지 없음")
  })
  @GetMapping("/{companyId}")
  public ResponseEntity<ApiResponseMessage<CompanyDetailResponseDto>> loadCompanyDetailData(
      
      @PathVariable Integer companyId
      
  ) {
    
    CompanyDto companyDetailData = companyService.getCompanyById(companyId)
        .orElse(null);

    if (companyDetailData == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponseMessage<>(false, "해당 업체가 존재하지 않습니다.", null));
    }

    CompanyDetailResponseDto response = CompanyDetailResponseDto.builder()
        .company(companyDetailData)
        .workplaces(workplaceService.findWorkplacesByCompanyId(companyId))
        .build();

    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", response)
    );
  }

  @Operation(
      summary = "측정대행 의뢰업체 정보 수정",
      description = "업체 정보를 수정합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "의뢰업체 정보 수정 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PatchMapping("/{companyId}")
  public ResponseEntity<?> updateCompanyProfile(
      
      @PathVariable Integer companyId,
      @Valid @RequestBody CompanyUpdateRequestDto requestDto,
      BindingResult bindingResult
      
  ) {
    
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    
    companyService.updateCompanyProfile(companyId, requestDto);
    
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "측정대행 의뢰업체 데이터 삭제")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "의뢰업체 삭제 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @DeleteMapping("/{companyId}")
  public ResponseEntity<?> deleteCompanyById(
      
      @PathVariable Integer companyId
      
  ) {
    
    companyService.deleteCompany(companyId);
    
    return ResponseEntity.noContent().build();
  }
}
