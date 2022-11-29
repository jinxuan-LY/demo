package org.example.qr.resolver.field;

import org.example.qr.bo.QRCodeResult;
import org.example.qr.bo.QRCodeResult.MerchantAccountInfo;
import org.example.qr.resolver.CommonFieldResolver;
import org.example.qr.resolver.field.mai.MerchantAccountInfoCodeResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商户账户信息-解析器
 *
 * @author:guyang.huang
 * @date:2022/2/9 8:23 下午
 */
@Component
public class MerchantAccountInfoFieldResolver implements CommonFieldResolver {

    /**
     * 国家通用商户字段ID
     */
    public static final String NATIONAL_MERCHANT_FIELD_ID = "51";

    @Resource
    private MerchantAccountInfoCodeResolver merchantAccountInfoCodeResolver;

    @Override
    public String[] getIDs() {
        return new String[]{"26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", NATIONAL_MERCHANT_FIELD_ID};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length < 100;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        MerchantAccountInfo merchantAccountInfo = merchantAccountInfoCodeResolver.resolveQRCode(value);
        merchantAccountInfo.setNationalMerchant(NATIONAL_MERCHANT_FIELD_ID.equals(fieldId));
        if (result.getMerchantAccounts() == null) {
            List<MerchantAccountInfo> list = new ArrayList<>();
            list.add(merchantAccountInfo);
            result.setMerchantAccounts(list);
        } else {
            result.getMerchantAccounts().add(merchantAccountInfo);
        }
    }
}
