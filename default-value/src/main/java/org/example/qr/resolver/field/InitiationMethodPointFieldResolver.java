package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.QRInitiationMethod;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 初始方式-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
@Slf4j
public class InitiationMethodPointFieldResolver implements CommonFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"01"};
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
        QRInitiationMethod qrInitiationMethod = QRInitiationMethod.getQRInitiationMethod(value);
        if (qrInitiationMethod == null) {
            log.warn("Illegal point of Initiation Method:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
        result.setQrInitiationMethod(qrInitiationMethod);
    }
}
