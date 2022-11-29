package org.example.qr.resolver.field;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import org.springframework.stereotype.Component;

/**
 * 商户类别编码MCC-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
@Slf4j
public class MerchantCategoryCodeFieldResolver implements CommonFieldResolver {
    @Override
    public String[] getIDs() {
        return new String[]{"52"};
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
        if (!NumberUtils.isDigits(value)) {
            log.warn("Illegal merchant category code:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
        result.setMerchantCategoryCode(value);
    }
}
