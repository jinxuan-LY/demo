package org.example.alert.original;

import lombok.Data;

import java.util.List;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-11-29 21:42:00
 */
@Data
public class AlertRules {
    private List<Rule> alert_rules;
    private Integer total_size;
}
