package com.study.note.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("note")
public class Note {

    //备忘录主键ID
    @TableId("RecordID")
    private String recordID;

    //标题
    private String title;

    //正文
    private String text;

    //创建时间
    private Date createDate;

    //用户ID
    private String userId;
}
