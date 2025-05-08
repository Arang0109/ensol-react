package com.ensolution.ensol.mapper.auth;

import com.ensolution.ensol.dto.auth.PermissionDto;
import com.ensolution.ensol.entity.auth.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
  PermissionDto toDto(Permission permission);
  Permission toEntity(PermissionDto permissionDto);
}
