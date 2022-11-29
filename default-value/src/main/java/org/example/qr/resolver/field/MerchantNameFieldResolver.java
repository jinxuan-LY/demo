package org.example.qr.resolver.field;

import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import org.springframework.stereotype.Component;

/**
 * 商户名称-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
public class MerchantNameFieldResolver implements CommonFieldResolver {

    @Override
    public String[] getIDs() {
        return new String[]{"59"};
    }

    @Override
    public boolean mandatory() {
        return true;
    }

    @Override
    public boolean checkLength(int length) {
        return length >= 1 && length <= 25;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        result.setMerchantName(value);
    }
}
