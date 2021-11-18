package com.study.note.controller;

import com.study.note.common.DataResponse;
import com.study.note.entity.Note;
import com.study.note.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Note")
@Api("备忘录")
public class NoteController {

    @Autowired
    private NoteService noteService;

    //新增备忘录
    @PostMapping("/addnote")
    @ApiOperation(value = "新增备忘录")
    @ApiImplicitParam(name = "params", value = "新增备忘录内容", required = true, dataType = "Note")
    public DataResponse add(@RequestBody Map<String, Object> params) {
        return this.noteService.add(params);
    }

    //修改备忘录
    @PostMapping("/updatenote")
    @ApiOperation(value = "修改备忘录")
    @ApiImplicitParam(name = "params", value = "修改备忘录内容", required = true, dataType = "Note")
    public DataResponse update(@RequestBody Map<String, Object> params) {
        return this.noteService.update(params);
    }

    //删除备忘录
    @PostMapping("/deletenote")
    @ApiOperation(value = "删除备忘录")
    @ApiImplicitParam(name = "params", value = "删除备忘录内容", required = true, dataType = "Note")
    public DataResponse delete(@RequestBody Map<String, Object> params) {
        return this.noteService.delete(params);
    }

    //分页查找备忘录
    @PostMapping("/selectnote")
    @ApiOperation(value = "分页查找备忘录")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int")
    public List<Note> select(@RequestParam int pageNum) {
        List<Note> pageList = noteService.select(pageNum);
        return pageList;
    }
}
