package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 交易金额-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
@Slf4j
public class TransactionAmountFieldResolver implements CommonFieldResolver {

    public static final String ZERO_DECIMAL = ".00";
    public static final String POINT_SYMBOL = ".";

    @Override
    public String[] getIDs() {
        return new String[]{"54"};
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
        if (!NumberUtils.isNumber(value)) {
            logAndThrowException(value);
        } else if (!NumberUtils.isDigits(value) && (!value.endsWith(ZERO_DECIMAL) && !value.endsWith(POINT_SYMBOL))) {//不是纯数字有两种规则:1.".00"结尾 2."."结尾
            logAndThrowException(value);
        }

        //检查金额是否大于0
        BigDecimal qrAmount = new BigDecimal(value);
        if (qrAmount.compareTo(BigDecimal.ZERO) <= 0) {
            logAndThrowException(value);
        }

        //抹零处理，三种情况：100.00->100,100.->100,100->100
        //保存金额
        result.setTransactionAmount(qrAmount.stripTrailingZeros().toPlainString());
    }

    /**
     * 打印日志并抛出异常
     *
     * @param value
     */
    private void logAndThrowException(String value) {
        log.warn("Illegal transaction amount:{}.", value);
        throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
    }

}
