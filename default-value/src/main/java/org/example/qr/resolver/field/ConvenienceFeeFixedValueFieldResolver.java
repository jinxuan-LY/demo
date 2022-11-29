package org.example.qr.resolver.field;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import lombok.extern.slf4j.Slf4j;

/**
 * Value of Convenience Fee Fixed
 *
 * @author:guyang.huang
 * @date:2022/3/7 8:12 下午
 */
//@Component
@Slf4j
public class ConvenienceFeeFixedValueFieldResolver implements CommonFieldResolver {

    public static final String INTEGER_ZERO = "0";
    
    public static final String DECIMAL_ZERO = "0.00";

    public static final String POINT_ZERO = "0.";

    @Override
    public String[] getIDs() {
        return new String[]{"56"};
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
        if (!INTEGER_ZERO.equals(value) && !DECIMAL_ZERO.equals(value) && !POINT_ZERO.equals(value)) {
            log.warn("Illegal ConvenienceFeeFixedValue:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
    }

}
