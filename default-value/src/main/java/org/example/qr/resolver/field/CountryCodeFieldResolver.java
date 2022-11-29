package org.example.qr.resolver.field;

import lombok.extern.slf4j.Slf4j;
import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import org.springframework.stereotype.Component;

/**
 * 国家编码-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
@Slf4j
public class CountryCodeFieldResolver implements CommonFieldResolver {

    public static final String ID = "ID";

    @Override
    public String[] getIDs() {
        return new String[]{"58"};
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
        if (!ID.equals(value)) {
            log.warn("Illegal country code:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
        result.setCountryCode(value);
    }
}
