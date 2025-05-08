package com.ensolution.ensol.dto.app.entity.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkplaceDto {
  
  @Schema(
      description = "측정대상 사업장 ID",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Integer workplaceId;
  
  @Schema(
      description = "측정대행 의뢰업체 Id",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Integer companyId;
  
  @Schema(description = "측정대상 사업장 이름", example = "테스트 사업장")
  @NotBlank(message = "필수 기입")
  private String workplaceName;
  
  @Schema(description = "사업장 종별", example = "1종")
  private char workplaceSize;
  
  @Schema(description = "주소", example = "부산진구 동평로 291번길 30 1층")
  private String address;
  
  @Schema(description = "주 생산품", example = "타이어")
  private String mainProduction;
  
  @Schema(description = "업종", example = "타이어 제조업")
  private String businessType;
  
  @Schema(
      description = "등록일", example = "1999-01-01",
      accessMode = Schema.AccessMode.READ_ONLY)
  private LocalDate regDate;
}