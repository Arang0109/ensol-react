package com.ensolution.ensol.dto.auth;

import com.ensolution.ensol.entity.auth.Permission;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleDto {
  private Integer roleId;
  private String roleName;
}
