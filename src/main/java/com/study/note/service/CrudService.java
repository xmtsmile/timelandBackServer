package com.study.note.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.study.note.common.DataResponse;
import com.study.note.common.BizSuccessEnum;
import com.study.note.entity.Crud;
import com.study.note.mapper.CrudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CrudService {

    @Autowired
    private CrudMapper crudMapper;

    //新增
    public DataResponse add(Map<String, Object> params) {
        Crud crud = JSON.parseObject(JSON.toJSONString(params), Crud.class);
        this.crudMapper.insert(crud);
        return new DataResponse().defaultOperationResponse(BizSuccessEnum.BUSINESS_OPERATE_SUCCESS.getMessage());
    }

    //修改
    public DataResponse update(Map<String, Object> params) {
        Crud crud = JSON.parseObject(JSON.toJSONString(params), Crud.class);
        UpdateWrapper<Crud> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Crud::getId, crud.getId())
                .set(Crud::getName, crud.getName());
        this.crudMapper.update(null, updateWrapper);

        return new DataResponse().defaultOperationResponse(BizSuccessEnum.BUSINESS_OPERATE_SUCCESS.getMessage());
    }

    //删除
    public DataResponse delete(Map<String, Object> params) {
        Crud crud = JSON.parseObject(JSON.toJSONString(params), Crud.class);
        Crud keyObj = new Crud();
        keyObj.setId(crud.getId());
        this.crudMapper.delete(new QueryWrapper<>(keyObj));
        return new DataResponse().defaultOperationResponse(BizSuccessEnum.BUSINESS_OPERATE_SUCCESS.getMessage());
    }

    //根据id查找
    public DataResponse selectById(Map params) {
        List list = this.crudMapper.selectById(params);
        return new DataResponse().defaultOperationResponse(BizSuccessEnum.BUSINESS_QUERY_SUCCESS.getMessage(), list);
    }
}


