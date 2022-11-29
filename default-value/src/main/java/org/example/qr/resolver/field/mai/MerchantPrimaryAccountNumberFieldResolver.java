package org.example.qr.resolver.field.mai;

import org.example.qr.BizErrorType;
import org.example.qr.BizException;
import org.example.qr.LuhnUtil;
import org.example.qr.bo.QRCodeResult.MerchantAccountInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 * 商户账号MPAN-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/11 11:16 上午
 */
@Component
@Slf4j
public class MerchantPrimaryAccountNumberFieldResolver implements MerchantAccountInfoFieldResolver {

    public static final int NNS_LENGTH = 8;

    @Override
    public String[] getIDs() {
        return new String[]{"01"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length > 9 && length <= 19;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, MerchantAccountInfo result) {
        if (!NumberUtils.isDigits(value)) {
            log.warn("Illegal merchant pan:{}.", value);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }

        if (!setMpan(value, result)) {
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
    }

    /**
     * 设置MPAN相关的值到merchantAccountInfo里面
     *
     * @param mpan
     * @param merchantAccountInfo
     * @return 设置失败返回false
     */
    public static boolean setMpan(String mpan, MerchantAccountInfo merchantAccountInfo) {
        if (StringUtils.isBlank(mpan) || mpan.length() < NNS_LENGTH || merchantAccountInfo == null) {
            return false;
        }
        merchantAccountInfo.setNns(mpan.substring(0, NNS_LENGTH));
        // 支付解析二维码不添加校验位
        merchantAccountInfo.setMpan(mpan);
        return true;
    }
}
