package com.blue.halo.repository;

import com.blue.halo.Dto.IdNameDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NameMapper {

    public IdNameDto getAnswer(int idNumber);
}
