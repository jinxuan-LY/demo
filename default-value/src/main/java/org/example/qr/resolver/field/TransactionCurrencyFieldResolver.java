package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 交易币种-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
@Slf4j
public class TransactionCurrencyFieldResolver implements CommonFieldResolver {

    public static final String IDR = "360";

    @Override
    public String[] getIDs() {
        return new String[]{"53"};
    }

    @Override
    public boolean mandatory() {
        return true;
    }

    @Override
    public boolean checkLength(int length) {
        return length == 3;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        if (!IDR.equals(value)) {
            log.warn("Illegal transaction currency:{}.", value);
            throw new BizException(BizErrorType.QR_CURRENCY_ILLEGAL);
        }
    }
}
