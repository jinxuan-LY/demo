package org.example.qr.resolver.field.mai;

import org.example.qr.bo.QRCodeResult.MerchantAccountInfo;
import org.springframework.stereotype.Component;

/**
 * 域名-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/11 11:16 上午
 */
@Component
public class GloballyUniqueIdentifierFieldResolver implements MerchantAccountInfoFieldResolver {

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
        return length >= 1 && length <= 32;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, MerchantAccountInfo result) {

    }
}
