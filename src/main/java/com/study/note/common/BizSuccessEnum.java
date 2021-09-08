package com.study.note.common;

/**
 * @author 王者奉
 * @date 2021/3/1 9:37
 */
public enum BizSuccessEnum implements AbstractBaseExceptionEnum {

    /**
     * 常用业务成功信息
     *
     * @param code
     * @param message
     */
    BUSINESS_OPERATE_SUCCESS(200, "操作成功!"),
    BUSINESS_QUERY_SUCCESS(200, "查询成功!"),
    BUSINESS_UPDATE_SUCCESS(200, "编辑成功!"),
    BUSINESS_DELETE_SUCCESS(200, "删除成功!"),
    BUSINESS_INSERT_SUCCESS(200, "新增成功!"),
    BUSINESS_SUB_SUCCESS(200, "交审成功!"),
    BUSINESS_EXAM_SUCCESS(200, "审核成功!"),
    BUSINESS_APPR_SUCCESS(200, "批准成功!"),
    BUSINESS_BACKFILL_SUCCESS(200, "进度回填成功!"),
    BUSINESS_UPGRADE_SUCCESS(200, "升版成功!"),
    BUSINESS_ANNUL_SUCCESS(200, "作废成功!"),
    BUSINESS_BACK_SUCCESS(200, "退回成功!"),
    BUSINESS_IMPORT_SUCCESS(200, "导入成功!"),
    BUSINESS_ENTRUST_SUCCESS(200, "委托成功!"),
    BUSINESS_PRINT_SUCCESS(200, "打印成功!"),
    BUSINESS_REPORT_SUCCESS(200, "报审成功!"),
    BUSINESS_SENDCHECK_SUCCESS(200, "送检成功!"),
    BUSINESS_CONFIRM_SUCCESS(200, "确认完成!"),
    BUSINESS_ASSIGN_SUCCESS(200, "分配成功!"),
    BUSINESS_RETURNOBJ_SUCCESS(200, "归还成功!"),
    BUSINESS_MAINTENANCE_SUCCESS(200, "维护保养成功!");

//    BUSINESS_STATUE_SUCCESS(200, "数据查无此状态值");


    private Integer code;
    private String message;

    BizSuccessEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
