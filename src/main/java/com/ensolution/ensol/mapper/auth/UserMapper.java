package com.ensolution.ensol.mapper.auth;

import com.ensolution.ensol.dto.auth.UserDto;
import com.ensolution.ensol.entity.auth.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
  User toEntity(UserDto userDto);
  List<UserDto> toDtoList(List<User> users);
}
