package com.blue.halo.repository;

import com.blue.halo.Dto.IdNameDto;
import com.blue.halo.Dto.IdWordDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NameMapper {

    public IdNameDto getAnswer(int idNumber);

    public IdWordDto getWord(int idNumber);
}
