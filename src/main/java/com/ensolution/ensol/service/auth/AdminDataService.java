package com.ensolution.ensol.service.auth;

import com.ensolution.ensol.dto.auth.RoleDto;
import com.ensolution.ensol.dto.auth.UserDto;
import com.ensolution.ensol.entity.auth.Role;
import com.ensolution.ensol.entity.auth.User;
import com.ensolution.ensol.mapper.auth.RoleMapper;
import com.ensolution.ensol.mapper.auth.UserMapper;
import com.ensolution.ensol.repository.auth.RoleRepository;
import com.ensolution.ensol.repository.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminDataService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;
  private final PasswordEncoder passwordEncoder;

  public List<UserDto> findAllUSers() {
    List<User> users = userRepository.findAll();
    return userMapper.toDtoList(users);
  }

  public List<RoleDto> findAllRoles() {
    List<Role> roles = roleRepository.findAll();
    return roleMapper.toDtoList(roles);
  }

  public void createUser(UserDto userDto, RoleDto roleDto) {
    String rawPassword = userDto.getPassword();
    String encodedPassword = passwordEncoder.encode(rawPassword);

    User user = userMapper.toEntity(userDto);
    user.setPassword(encodedPassword);

    Set<Role> roles = new HashSet<>();
    roles.add(roleMapper.toEntity(roleDto));
    user.setRoles(roles);

    userRepository.save(user);
  }
}
