package com.ensolution.ensol.dto.app.entity.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDto {
  @Schema(
      description = "측정대행 의뢰업체 ID",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Integer companyId;

  @Schema(description = "의뢰업체 이름", example = "테스트 업체")
  @NotBlank(message = "필수 기입")
  private String companyName;

  @Schema(description = "주소", example = "부산진구 동평로 291번길 30 1층")
  private String address;

  @Schema(description = "대표자", example = "홍길동")
  @NotBlank(message = "필수 기입")
  private String ceoName;

  @Schema(description = "사업자 번호", example = "123-45-67891")
  @NotBlank(message = "필수 기입")
  @Pattern(regexp = "\\d{3}-\\d{2}-\\d{5}", message = "사업자 번호 형식은 000-00-00000이어야 합니다.")
  private String bizNumber;

  @Schema(
      description = "등록일", example = "1999-01-01",
      accessMode = Schema.AccessMode.READ_ONLY)
  private LocalDate regDate;
}