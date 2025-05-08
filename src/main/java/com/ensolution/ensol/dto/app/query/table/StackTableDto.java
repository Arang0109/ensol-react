package com.ensolution.ensol.dto.app.query.table;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackTableDto {
  private Integer stackId;
  private String stackName;
  private String companyName;
  private String workplaceName;
  private String stackType;
  private char stackSize;
  private String prevention;
}
