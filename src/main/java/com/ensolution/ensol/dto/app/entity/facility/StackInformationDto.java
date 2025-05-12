package com.ensolution.ensol.dto.app.entity.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackInformationDto {
  @Schema(
      description = "배출시설 ID",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Integer stackId;
  
  @Schema(description = "직경 (가로)", example = "10.000")
  private Double diameter;
  
  @Schema(description = "직경 (세로)", example = "10.000")
  private Double diameter2;
  
  @Schema(description = "설계 유량", example = "100,000.000")
  private Double quantity;
  
  @Schema(description = "배출구 높이", example = "10.000")
  private Double height;
  
  @Schema(description = "배출구 모양", example = "사각형")
  private String stackShape;
  
  @Schema(description = "배출구 방향", example = "수직")
  private String stackDirection;
}