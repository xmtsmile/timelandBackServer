package com.study.note.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.study.note.common.BizSuccessEnum;
import com.study.note.common.DataResponse;
import com.study.note.entity.Note;
import com.study.note.mapper.NoteMapper;
import com.study.note.utils.token.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private TokenHelper tokenHelper;

    //新增
    public DataResponse add(Map<String, Object> params) {
        Note note = JSON.parseObject(JSON.toJSONString(params), Note.class);
        note.setUserId(tokenHelper.get(note.getUserId()));
        note.setRecordID(UUID.randomUUID().toString());
        note.setCreateDate(new Date());
        this.noteMapper.insert(note);
        return new DataResponse().defaultOperationResponse(BizSuccessEnum.BUSINESS_OPERATE_SUCCESS.getMessage());
    }

    //修改
    public DataResponse update(Map<String, Object> params) {
        Note note = JSON.parseObject(JSON.toJSONString(params), Note.class);
        UpdateWrapper<Note> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Note::getUserId, tokenHelper.get(note.getUserId()))
                .set(Note::getTitle, note.getTitle())
                .set(Note::getText, note.getText());
        this.noteMapper.update(null, updateWrapper);

        return new DataResponse().defaultOperationResponse(BizSuccessEnum.BUSINESS_OPERATE_SUCCESS.getMessage());
    }

    //删除
    public DataResponse delete(Map<String, Object> params) {
        Note note = JSON.parseObject(JSON.toJSONString(params), Note.class);
        this.noteMapper.delete(tokenHelper.get(note.getUserId()));
        return new DataResponse().defaultOperationResponse(BizSuccessEnum.BUSINESS_OPERATE_SUCCESS.getMessage());
    }

    //查找
    public List<Note> select(int pageNum) {
//        PageHelper.startPage(1, 3);
        pageNum = pageNum * 3;
        return noteMapper.selectByPages(pageNum);
    }
}
