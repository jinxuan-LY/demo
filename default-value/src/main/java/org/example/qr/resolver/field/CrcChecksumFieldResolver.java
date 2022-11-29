package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.CRC16Util;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 校验码-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
@Slf4j
public class CrcChecksumFieldResolver implements CommonFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"63"};
    }

    @Override
    public boolean mandatory() {
        return true;
    }

    @Override
    public boolean checkLength(int length) {
        return length == 4;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        String wholeQRCode = result.getWholeQRCode();
        String sourceCode = wholeQRCode.substring(0, wholeQRCode.length() - 4);
        String crc16 = CRC16Util.crc16_CCITT_FALSE(sourceCode.getBytes());
        if (!crc16.equals(value)) {
            log.warn("Illegal CRC:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
    }

}
