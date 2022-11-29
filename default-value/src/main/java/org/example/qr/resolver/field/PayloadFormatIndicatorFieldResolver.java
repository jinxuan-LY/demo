package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 * 内容格式申明-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
@Slf4j
public class PayloadFormatIndicatorFieldResolver implements CommonFieldResolver {

    public static final String MPM = "01";

    @Override
    public String[] getIDs() {
        return new String[]{"00"};
    }

    @Override
    public boolean mandatory() {
        return true;
    }

    @Override
    public boolean checkLength(int length) {
        return length == 2;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        if (!NumberUtils.isDigits(value)) {
            log.warn("Illegal payload format indicator:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
        if (!MPM.equals(value)) {
            log.warn("Illegal payload format indicator:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
    }
}
