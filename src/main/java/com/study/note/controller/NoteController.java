package com.study.note.controller;

import com.study.note.common.DataResponse;
import com.study.note.entity.Note;
import com.study.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    //新增备忘录
    @PostMapping("/addnote")
    public DataResponse add(@RequestBody Map<String, Object> params) {
        return this.noteService.add(params);
    }

    //修改备忘录
    @PostMapping("/updatenote")
    public DataResponse update(@RequestBody Map<String, Object> params) {
        return this.noteService.update(params);
    }

    //删除备忘录
    @PostMapping("/deletenote")
    public DataResponse delete(@RequestBody Map<String, Object> params) {
        return this.noteService.delete(params);
    }

    //分页查找备忘录
    @PostMapping("/selectnote")
    public List<Note> select(@RequestParam int pageNum) {
        List<Note> pageList = noteService.select(pageNum);
        return pageList;
    }
}
