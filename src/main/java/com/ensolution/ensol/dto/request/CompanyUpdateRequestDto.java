package com.ensolution.ensol.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyUpdateRequestDto {
  
  @Schema(description = "측정대행 의뢰업체 ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer companyId;
  
  @NotBlank(message = "의뢰업체 이름은 필수입니다.")
  @Schema(description = "의뢰업체 이름", example = "테스트 업체")
  private String companyName;
  
  @NotBlank(message = "대표자 이름은 필수입니다.")
  @Schema(description = "대표자", example = "홍길동")
  private String ceoName;
  
  @NotBlank(message = "사업자 번호는 필수입니다.")
  @Pattern(regexp = "\\d{3}-\\d{2}-\\d{5}", message = "사업자 번호 형식은 000-00-00000이어야 합니다.")
  @Schema(description = "사업자 번호", example = "123-45-67891")
  private String bizNumber;
  
  @Schema(description = "주소", example = "부산진구 동평로 291번길 30 1층")
  private String address;
}
