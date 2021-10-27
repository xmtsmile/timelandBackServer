package com.study.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.note.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoteMapper extends BaseMapper<Note> {
    List<Note> selectByPages(int pageNum);
}
