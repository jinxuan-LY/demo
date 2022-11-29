package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;

/**
 * 拒绝出现code码-字段解析器
 */
//@Component
@Slf4j
public class RejectCodeFieldResolver implements CommonFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"55","57"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return true;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        log.warn("RejectCodeFieldResolver Illegal fieldId:{} value:{}", fieldId, value);
        //二维码不允许带有标签 55 或标签 57
        throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
    }
}
