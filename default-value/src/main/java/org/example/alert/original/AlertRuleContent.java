package org.example.alert.original;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-11-29 16:55:00
 */
@Data
public class AlertRuleContent {

    private Content content;
    private String severity;

    @Data
    public static class Content{
        @JSONField(name = "for")
        private String forTime;
        private String expr;
        private MessageTemplate message_template;
        private String evaluation_interval;
        private String built_in_evaluation_interval;
    }

    @Data
    public static class MessageTemplate{
        private String firing_message;
        private String resolved_message;
    }
}
