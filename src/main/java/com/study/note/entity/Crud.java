package com.study.note.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("study_jyz")
public class Crud {
    @TableId("id")
    private String id;

    private String name;
}
