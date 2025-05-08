package com.ensolution.ensol.service.auth;

import com.ensolution.ensol.dto.auth.RoleDto;
import com.ensolution.ensol.dto.auth.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
  private final AdminDataService adminDataService;

  public List<UserDto> findAllUsers() {
    return adminDataService.findAllUSers();
  }
  public List<RoleDto> findAllRoles() { return adminDataService.findAllRoles(); }

  public void createUser(UserDto userDto, RoleDto roleDto) {
    adminDataService.createUser(userDto, roleDto);
  }
}
