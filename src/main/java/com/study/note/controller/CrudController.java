package com.study.note.controller;

import com.study.note.common.DataResponse;
import com.study.note.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/noAuth/crud")
public class CrudController {

    @Autowired
    private CrudService crudService;

    //新增
    @PostMapping("/add")
    public DataResponse add(@RequestBody Map<String, Object> params) {
        return this.crudService.add(params);
    }

    //修改
    @PostMapping("/update")
    public DataResponse update(@RequestBody Map<String, Object> params) {
        return this.crudService.update(params);
    }

    //删除
    @PostMapping("/delete")
    public DataResponse delete(@RequestBody Map<String, Object> params) {
        return this.crudService.delete(params);
    }

    //查找
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public DataResponse selectById(@RequestBody(required = false) Map<String, Object> params) {

        return this.crudService.selectById(params);
    }
}
