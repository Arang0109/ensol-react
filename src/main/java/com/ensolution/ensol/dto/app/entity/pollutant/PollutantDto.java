package com.ensolution.ensol.dto.app.entity.pollutant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PollutantDto {
  @Schema(description = "오염물질 ID", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer pollutantId;
  
  @Schema(description = "오염물질 이름(한글)", example = "질소산화물")
  private String pollutantNameKR;
  
  @Schema(description = "오염물질 이름(영어)", example = "NOx")
  private String pollutantNameEN;
  
  @Schema(description = "오염물질 이름(현대자동차)", example = "질소산화물")
  private String PollutantNameHyundai;
  
  @Schema(description = "측정 방법", example = "현장측정")
  private String method;
  
  @Schema(description = "채취 시간", example = "15")
  private Integer samplingTime;
  
  @Schema(description = "채취 용량", example = "100")
  private Integer samplingVolume;
  
  @Schema(description = "분석 장비", example = "자동측정법/자동연속장치")
  private String analysisEquipment;
  
  @Schema(description = "문서 번호", example = "ES 01308.1b")
  private String legislationNumber;
}
