package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.QRTipTypeEnum;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 小类类型字段解析器
 */
@Component
@Slf4j
public class TipTypeFieldResolver implements CommonFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"55"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length == 2;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {

        QRTipTypeEnum qrTipTypeEnum = QRTipTypeEnum.fromType(value);
        if (Objects.isNull(qrTipTypeEnum)) {
            logAndThrowException(fieldId, value);
        }

        result.setTipType(value);
    }

    private void logAndThrowException(String fieldId, String value) {
        log.warn("TipTypeFieldResolver Illegal fieldId:{} value:{}", fieldId, value);
        throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
    }
}
