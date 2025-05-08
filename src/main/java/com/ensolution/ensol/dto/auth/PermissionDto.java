package com.ensolution.ensol.dto.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PermissionDto {
  private Integer permissionId;
  private String permissionName;
}
