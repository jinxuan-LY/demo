package org.example.alert.target;

import lombok.Data;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-11-29 22:11:00
 */
@Data
public class StdRuleDTO{
    private String ruleId;
    private String serviceName;
    // title
    private String displayName;
    // Critical //Error    //Warn
    private String severity;
    private String expression;
    private String duration;
    private String alertGroup;
    private String call;
    // status: 开启、关闭
    private String status;
}
