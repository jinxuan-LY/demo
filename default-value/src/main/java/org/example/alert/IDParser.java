package org.example.alert;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-11-30 00:14:00
 */

public class IDParser extends AbstractParser {
    private static final String sourcePath = "/Users/yuanqiang.liao/Downloads/alertRules/";
    private static final String targetPath = "/Users/yuanqiang.liao/Downloads/alertRules/target/";

    public static void main(String[] args) throws IOException {
        IDParser idParser = new IDParser();
        idParser.parse();
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }


    private static final Map<String, String> serviceNameMap;

    static {
        serviceNameMap = new HashMap<>();
        serviceNameMap.put("payment-tranonline-gw", "payment-tranonline-gw");
        serviceNameMap.put("payment-sequence", "payment-sequence");
        serviceNameMap.put("smart-payment", "smart-payment");
        serviceNameMap.put("payment-consumer", "payment-consumer");
        serviceNameMap.put("bbw-agent", "payment-consumer");
        serviceNameMap.put("bbwagent", "payment-consumer");
        serviceNameMap.put("payment-skn", "payment-skn");
        serviceNameMap.put("payment-rtgs", "payment-rtgs");
        serviceNameMap.put("rtgs", "payment-rtgs");

        serviceNameMap.put("auto-recon-sftp-agent", "auto-recon-sftp-agent");
        serviceNameMap.put("auto-recon-service", "auto-recon-service");
        serviceNameMap.put("payment-auth", "payment-auth");
        serviceNameMap.put("payment-tranonline-web-gw", "payment-tranonline-web-gw");
        serviceNameMap.put("ALTOPAY", "ALTOPAY");
        serviceNameMap.put("qris", "QRIS");
        serviceNameMap.put("alto-service", "alto-service");
        serviceNameMap.put("pmt-altoagent", "altoagent");
        serviceNameMap.put("pmt-alto-agent", "altoagent");
        serviceNameMap.put("alto-agent", "altoagent");
        serviceNameMap.put("pmt-aj-service", "aj-service");
        serviceNameMap.put("pmt-aj-agent", "aj-agent");
        serviceNameMap.put("pmt-bi-fast-service", "bi-fast");
        serviceNameMap.put("bi-fast service", "bi-fast");
        serviceNameMap.put("bi-fastagent", "bi-fast");
        serviceNameMap.put("bi-fast-agent", "bi-fast");
        serviceNameMap.put("pmt-saa-service", "saa-service");

        serviceNameMap.put("acquiring-gw", "acquiring-gw");
        serviceNameMap.put("acquiring-dd", "acquiring-dd");
        serviceNameMap.put("acquiring-va", "acquiring-va");
        serviceNameMap.put("sipp-agent", "acquiring-va");
        serviceNameMap.put("[PMT] VA ", "acquiring-va");
        serviceNameMap.put("acquiring-mix", "acquiring-mix");
        serviceNameMap.put("acquiring-payroll", "acquiring-payroll");
        serviceNameMap.put("acquiring-manager", "acquiring-manager");
        serviceNameMap.put("acquiring-auth", "acquiring-auth");
        serviceNameMap.put("acquiring-web-gw", "acquiring-web-gw");

        serviceNameMap.put("JVM", "JVM");
    }

    @Override
    protected List<String> getServiceNameList() {
        return new ArrayList<>(serviceNameMap.keySet());
    }

    @Override
    protected String getServiceName(String key) {
        return serviceNameMap.get(key) != null ? serviceNameMap.get(key) : key;
    }

    protected String buildCall(JSONObject alert_rule_labels) {
        String critical = "critical";
        if (StringUtils.equals(alert_rule_labels.getString("Payment-Tech"), critical)
                || StringUtils.equals(alert_rule_labels.getString("Payment-Bussiness"), critical)) {
            return "是";
        } else {
            return "否";
        }
    }
}
