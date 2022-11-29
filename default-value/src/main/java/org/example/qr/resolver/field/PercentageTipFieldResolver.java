package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 百分比金额小费字段解析器
 */
@Component
@Slf4j
public class PercentageTipFieldResolver implements CommonFieldResolver {

    public final static BigDecimal BIGDECIMAL_OF_HUNDRED = new BigDecimal("100");

    @Override
    public String[] getIDs() {
        return new String[]{"57"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length >= 1 && length <= 5;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {

        //检查value百分数是否符合0.01-99.99
        BigDecimal qrPercentageTipAmount = null;
        try {
            qrPercentageTipAmount = new BigDecimal(value);
        } catch (Exception e) {
            logAndThrowException(fieldId, value);
        }
        if (qrPercentageTipAmount.scale() > 2 || qrPercentageTipAmount.compareTo(BigDecimal.ZERO) <= 0
                || qrPercentageTipAmount.compareTo(BIGDECIMAL_OF_HUNDRED) >= 0) {
            logAndThrowException(fieldId, value);
        }

        /**
         * 返回原始qrcode字段格式
         * 12.
         * 05.0
         * 00.10
         */
        result.setPercentageTip(value);
    }

    private void logAndThrowException(String fieldId, String value) {
        log.warn("PercentageTipFieldResolver Illegal fieldId:{}, value:{} is not qualified number", fieldId, value);
        throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
    }
}
