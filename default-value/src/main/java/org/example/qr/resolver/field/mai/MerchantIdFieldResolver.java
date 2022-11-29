package org.example.qr.resolver.field.mai;

import org.example.qr.bo.QRCodeResult.MerchantAccountInfo;
import org.springframework.stereotype.Component;

/**
 * 商户ID-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/11 11:16 上午
 */
@Component
public class MerchantIdFieldResolver implements MerchantAccountInfoFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"02"};
    }

    @Override
    public boolean mandatory() {
        return true;
    }

    @Override
    public boolean checkLength(int length) {
        return length >= 1 && length <= 15;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, MerchantAccountInfo result) {
        result.setMerchantId(value);
    }
}
