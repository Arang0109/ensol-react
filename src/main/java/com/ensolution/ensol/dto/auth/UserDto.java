package com.ensolution.ensol.dto.auth;

import com.ensolution.ensol.entity.auth.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
  private Integer userId;
  private String username;
  private String password;
  private String name;
  private String email;
  private String phone;
  private String department;
  private String position;
  private LocalDate joiningDate;
  private boolean enabled;

  private Set<Role> roles;
}
