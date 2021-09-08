package com.study.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.note.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

    User findByName(String name);

    User findById(String id);
}
