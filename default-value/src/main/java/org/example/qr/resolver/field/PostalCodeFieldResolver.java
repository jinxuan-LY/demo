package org.example.qr.resolver.field;

import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import org.springframework.stereotype.Component;

/**
 * 邮递区号-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
public class PostalCodeFieldResolver implements CommonFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"61"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length >= 1 && length <= 10;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        result.setPostalCode(value);
        result.setWholePostalCode(fieldId + length + value);
    }
}
