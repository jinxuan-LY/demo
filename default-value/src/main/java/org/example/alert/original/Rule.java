package org.example.alert.original;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-11-29 16:52:00
 */
@Data
public class Rule {
    private String rule_id;
    private String rule_label_org;
    private AlertRuleContent alert_rule_content;
    private JSONObject alert_rule_labels;
    private String rule_type;
    private String display_name;
    private String rule_desc;
    private String is_disabled;

}
