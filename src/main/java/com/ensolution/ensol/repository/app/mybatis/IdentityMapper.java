package com.ensolution.ensol.repository.app.mybatis;

import com.ensolution.ensol.dto.app.query.IdentityDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdentityMapper {
  IdentityDto getIds(Integer id);
}
