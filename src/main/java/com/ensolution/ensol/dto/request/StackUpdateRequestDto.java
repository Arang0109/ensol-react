package com.ensolution.ensol.dto.request;

import com.ensolution.ensol.dto.app.entity.facility.StackInformationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackUpdateRequestDto {
  
  @Schema(
      description = "배출시설 ID",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackId;
  
  @NotBlank(message = "배출시설은 필수 기입 항목입니다.")
  @Schema(description = "배출시설 이름", example = "테스트 베출시설")
  private String stackName;
  
  @Schema(description = "배출시설 종류", example = "배출시설 종류")
  private String stackType;
  
  @Schema(description = "배출시설 종별", example = "배출시설 종별")
  private char stackSize;
  
  @Schema(description = "방지시설 이름", example = "방지시설 이름")
  private String prevention;
  
  @Schema(description = "방지시설 용량", example = "방지시설 용량")
  private Long preventionCapacity;
  
  @Schema(description = "sems 번호", example = "sems 번호")
  private Long semsNumber;
  
  @Schema(description = "메모", example = "메모")
  private String note;
  
  private StackInformationDto stackInformation;
}