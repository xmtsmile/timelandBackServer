package com.study.note.controller;

import com.study.note.common.DataResponse;
import com.study.note.service.CrudService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/crud")
@Api("测试用")
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

    //测试
    @PostMapping("/test")
    public int[] plusOne(@RequestParam int[] digits) {
        String str = "";
        for (int digit : digits) {
            str += String.valueOf(digit);
        }
        int num = Integer.parseInt(str) + 1;
        str = String.valueOf(num);
        int[] newDigits = new int[str.length()];
        for(int j = 0; j < str.length(); j++)
        {
            String m = str.substring(j, j + 1);
            newDigits[j] = Integer.parseInt(m);
        }
        return newDigits;
    }
}
