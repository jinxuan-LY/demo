package org.example.alert;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.example.alert.original.AlertRules;
import org.example.alert.original.Rule;
import org.example.alert.target.StdRuleDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-11-29 17:11:00
 */
public abstract class AbstractParser {
    private String sourcePath = "/Users/yuanqiang.liao/Downloads/alertRules/";
    private String targetPath = "/Users/yuanqiang.liao/Downloads/alertRules/target/";

    protected void parse() {
        File file = new File(getSourcePath());
        for (File item : file.listFiles()) {
            if (item.isDirectory() || item.getName().startsWith(".")) continue;

            String ruleStr = ParserUtil.getStringFromFile(item);
            List<StdRuleDTO> ruleDTOList = this.parse2StdRuleDTO(ruleStr);
            ParserUtil.write2Excel(ruleDTOList, getTargetPath(), item.getName());
        }
    }

    public List<StdRuleDTO> parse2StdRuleDTO(String json) {
        AlertRules rule = JSONObject.parseObject(json, AlertRules.class);
        assert rule.getAlert_rules().size() == rule.getTotal_size();

        List<StdRuleDTO> stdRuleDTOList = new ArrayList<>();
        for (Rule item : rule.getAlert_rules()) {
            StdRuleDTO dto = new StdRuleDTO();
            dto.setRuleId(item.getRule_id());
            dto.setServiceName(buildServiceName(item.getDisplay_name()));
            dto.setDisplayName(item.getDisplay_name());
            dto.setStatus(StringUtils.equals(item.getIs_disabled(), "0") ? "开启" : "关闭");
            dto.setSeverity(item.getAlert_rule_content().getSeverity());
            dto.setExpression(item.getAlert_rule_content().getContent().getExpr());
            dto.setDuration(item.getAlert_rule_content().getContent().getForTime());
            dto.setAlertGroup(buildAlertGroup(item.getAlert_rule_labels()));
            dto.setCall(buildCall(item.getAlert_rule_labels()));

            stdRuleDTOList.add(dto);
        }

        // 过滤
        stdRuleDTOList = stdRuleDTOList.stream().filter(item -> !item.getAlertGroup().equals("unknown")).collect(Collectors.toList());
        // 按照服务名排序
        stdRuleDTOList.sort(new Comparator<StdRuleDTO>() {
            @Override
            public int compare(StdRuleDTO o1, StdRuleDTO o2) {
                // 优先按照告警等级排序
                if (o1.getSeverity().compareTo(o2.getSeverity()) == 0) {
                    // 再按照服务排序
                    return o1.getServiceName().compareTo(o2.getServiceName());
                } else {
                    return o1.getSeverity().compareTo(o2.getSeverity());
                }
            }
        });
        return stdRuleDTOList;
    }

    protected String buildCall(JSONObject alert_rule_labels){
        return "unknown";
    }


    abstract protected List<String> getServiceNameList();

    abstract protected String getServiceName(String key);

    private String buildServiceName(String display_name) {
        for (String item : getServiceNameList()) {
            if (StringUtils.containsAnyIgnoreCase(display_name, item)) {
                return getServiceName(item);
            }
        }
        return StringUtils.substring(display_name, 0, 30);
    }

    protected static String buildAlertGroup(JSONObject alert_rule_labels) {
        if (StringUtils.isNotBlank(alert_rule_labels.getString("Payment-Tech"))
                && StringUtils.isNotBlank(alert_rule_labels.getString("Payment-Bussiness"))) {
            return "技术&业务";
        } else if (StringUtils.isNotBlank(alert_rule_labels.getString("Payment-Tech"))) {
            return "技术";
        } else if (StringUtils.isNotBlank(alert_rule_labels.getString("Payment-Bussiness"))) {
            return "业务";
//        } else if (StringUtils.isNotBlank(alert_rule_labels.getString("id-banking_SeaBank-Payment-ID-Tech-Live_critical"))
//                || StringUtils.isNotBlank(alert_rule_labels.getString("id-banking_SeaBank-Payment-ID-Tech-Live_error"))
//        ) {
//            return "清算";
        } else {
            return "unknown";
        }
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }
}
