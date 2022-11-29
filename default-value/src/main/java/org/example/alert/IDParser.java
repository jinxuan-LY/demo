package org.example.alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    private static final List<String> serviceNameList;

    static {
        serviceNameList = new ArrayList<>();
        serviceNameList.add("payment-tranonline-gw");
        serviceNameList.add("payment-sequence");
        serviceNameList.add("smart-payment");
        serviceNameList.add("payment-consumer");
        serviceNameList.add("payment-skn");
        serviceNameList.add("payment-rtgs");
        serviceNameList.add("bbw-agent");
        serviceNameList.add("auto-recon-sftp-agent");
        serviceNameList.add("auto-recon-service");
        serviceNameList.add("payment-auth");
        serviceNameList.add("payment-tranonline-web-gw");
        serviceNameList.add("ALTOPAY");
        serviceNameList.add("alto-service");
        serviceNameList.add("pmt-altoagent");
        serviceNameList.add("pmt-aj-service");
        serviceNameList.add("pmt-aj-agent");
        serviceNameList.add("pmt-bi-fast-service");
        serviceNameList.add("bi-fast-agent");
        serviceNameList.add("pmt-saa-service");

        serviceNameList.add("acquiring-gw");
        serviceNameList.add("acquiring-va");
        serviceNameList.add("acquiring-dd");
        serviceNameList.add("acquiring-mix");
        serviceNameList.add("acquiring-payroll");
        serviceNameList.add("acquiring-manager");
        serviceNameList.add("sipp-agent");
        serviceNameList.add("acquiring-auth");
        serviceNameList.add("acquiring-web-gw");
    }

    @Override
    protected List<String> getServiceName() {
        return serviceNameList;
    }
}
