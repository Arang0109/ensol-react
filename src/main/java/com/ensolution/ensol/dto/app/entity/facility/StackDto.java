package com.ensolution.ensol.dto.app.entity.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackDto {
  
  @Schema(
      description = "배출시설 ID",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackId;
  
  @Schema(
      description = "측정대상 사업장 ID",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Integer workplaceId;
  
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
  
  @Schema(
      description = "등록일", example = "등록일(1999-01-01)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private LocalDate regDate;
  private StackInformationDto stackInformation;
}