package com.ensolution.ensol.dto.app.query;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HistoryDto {
  private Date measureDate;
  private Integer stackId;
  private Integer teamId;
  private String teamName;
  private String pollutantIds;
}
