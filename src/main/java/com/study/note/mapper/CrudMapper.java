package com.study.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.note.entity.Crud;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface CrudMapper extends BaseMapper<Crud> {
    List<Map<String, Object>> selectById(@Param("params") Map<String, Object> params);
}
