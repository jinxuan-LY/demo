package org.example.qr.resolver.field;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.example.qr.resolver.field.TransactionAmountFieldResolver.POINT_SYMBOL;
import static org.example.qr.resolver.field.TransactionAmountFieldResolver.ZERO_DECIMAL;

/**
 * 固定金额小费字段解析器
 */
@Component
@Slf4j
public class FixedTipFieldResolver implements CommonFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"56"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length >= 1 && length <= 13;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        //先校验是否为纯数字
        // 不是纯数字有两种规则:1.".00"结尾 2."."结尾 是符合条件的
        if (!NumberUtils.isNumber(value)) {
            logAndThrowException(fieldId, value);
        } else if (!NumberUtils.isDigits(value) && (!value.endsWith(ZERO_DECIMAL) && !value.endsWith(POINT_SYMBOL))) {
            logAndThrowException(fieldId, value);
        }

        //检查金额要大于0，小于等于最大限额（目前是999999）
        BigDecimal qrfixedTipAmount = new BigDecimal(value);
        if (qrfixedTipAmount.compareTo(BigDecimal.ZERO) <= 0 || qrfixedTipAmount.compareTo(new BigDecimal(999999)) > 0) {
            log.warn("FixedTipFieldResolver Illegal fieldId:{}, value:{} is more than max limit", fieldId, value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }

        //抹零处理，三种情况：100.00->100,100.->100,100->100
        //保存金额
        result.setFixedTip(qrfixedTipAmount.stripTrailingZeros().toPlainString());
        result.setEstimatedTipAmount(qrfixedTipAmount.stripTrailingZeros().toPlainString());
    }

    private void logAndThrowException(String fieldId, String value) {
        log.warn("FixedTipFieldResolver Illegal fieldId:{}, value:{} is not pure digit", fieldId, value);
        throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
    }
}
