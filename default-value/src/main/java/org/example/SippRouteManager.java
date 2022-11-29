package org.example;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author yuanqiang.liao
 * @description 调用sipp路由工具类
 * @createTime 2022-11-02 15:34:00
 */

@Component
public class SippRouteManager implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(SippRouteManager.class);
    // 调用 bank-api 开关开启。
    private static final String SWITCH_OPEN = "Y";
    private static final String SPILT_CHAR = ",";

    @Value("${bi.snap.switch:Y}")
    private String biSnapSwitch;

    private List<String> biSnapWhiteList;
    @Value("${bi.snap.percentage:51}")
    private Integer biSnapPercentage;


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("bi.snap.route.configuration. biSnapSwitch:{}, biSnapWhiteList:{}, biSnapPercentage:{}",
                biSnapSwitch, biSnapWhiteList, biSnapPercentage);
    }

    @Value("${bi.snap.whiteList}")
    public void setBiSnapWhiteList(List<String> biSnapWhiteList) {
        this.biSnapWhiteList = biSnapWhiteList;
    }

    /**
     * 判断 va 调用sipp是经过 sipp-agent 还是经过bank-api。
     *
     * @param bankRef
     * @return
     */
    public SippInvokeRouter getSippInvokeLink(String bankRef, String payerAcct) {
        // 1. 优先判断开关是否开启
        if (!StringUtils.equals(SWITCH_OPEN, biSnapSwitch)) {
            log.info("biSnapSwitch is closed. will invoke sipp-agent. bankRef:{}", bankRef);
            return SippInvokeRouter.SIPP_AGENT;
        }

        // 2. 再判断是否配置了白名单，如果配置了白名单，则忽略按比例分配流量。
        if (CollectionUtils.isNotEmpty(biSnapWhiteList)) {
            List<String> list = biSnapWhiteList;
            log.info("biSnapWhiteList is not empty, will route by whiteList and ignore percentage.");
            if (list.contains(payerAcct)) {
                log.info("{} is in the whiteList, will invoke bank-api. bankRef:{}", payerAcct, bankRef);
                return SippInvokeRouter.BANK_API;
            } else {
                log.info("{} is not in the whiteList, will invoke sipp-agent. bankRef:{}", payerAcct, bankRef);
                return SippInvokeRouter.SIPP_AGENT;
            }
        }

        // 3. 根据比例分配流量
        return this.routeByPercentage(bankRef);
    }

    private SippInvokeRouter routeByPercentage(String bankRef) {
        // 1. 比例未配置，默认走老链路
        if (biSnapPercentage == null) {
            log.info("doesn't configure percentage, invoke sipp-agent by default. bankRef:{}", bankRef);
            return SippInvokeRouter.SIPP_AGENT;
        }

        // 2. 计算随机数[0,99)，配置项大于随机数则走bank-api，否则走老链路：sipp-agent。
        int randomInt = calculatePercentage(bankRef);
        if (biSnapPercentage >= randomInt) {
            log.info("configuration great than random percentage. invoke bank-api. bankRef:{}", bankRef);
            return SippInvokeRouter.BANK_API;
        } else {
            log.info("configuration not great than random percentage. invoke bank-api. bankRef:{}", bankRef);
            return SippInvokeRouter.SIPP_AGENT;
        }
    }

    private static int calculatePercentage(String bankRef) {
        int defaultRandom = 100;
        try {
            // 1. 优先取BC流水号后两位作为随机数
            return Integer.parseInt(StringUtils.substring(bankRef, StringUtils.length(bankRef) - 2));
        } catch (Exception e) {
            // 2. 异常则程序生成随机数。
//            log.error("get random percentage by bc reference exception.", e);
            try {
                Random r = new Random();
                return r.nextInt(defaultRandom);
            } catch (Exception e1) {
                // 3. 再异常，返回默认值100. 此时仅当配置全部流量走bank-api才会走bank-api，否则走sipp-agent。
                log.error("generate random integer exception.", e1);
                return defaultRandom;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int random = calculatePercentage("BCsfjaks232523");
            System.out.println(random);
        }
    }
}
