package org.example.qr.resolver.field.mai;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult.MerchantAccountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商户标准-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/11 11:16 上午
 */
@Component
@Slf4j
public class MerchantCriteriaFieldResolver implements MerchantAccountInfoFieldResolver {

    public static final String UMI = "UMI";

    public static final String UKE = "UKE";

    public static final String UME = "UME";

    public static final String UBE = "UBE";

    public static final String URE = "URE";

    @Override
    public String[] getIDs() {
        return new String[]{"03"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length == 3;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, MerchantAccountInfo result) {
        if (!UMI.equals(value) && !UKE.equals(value) && !UME.equals(value) && !UBE.equals(value) && !URE.equals(value)) {
            log.warn("Illegal merchant criteria:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
        result.setMerchantCriteria(value);
    }
}
