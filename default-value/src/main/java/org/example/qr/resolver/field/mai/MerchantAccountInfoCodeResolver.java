package org.example.qr.resolver.field.mai;

import org.example.qr.bo.QRCodeResult.MerchantAccountInfo;
import org.example.qr.resolver.BaseAtLeastOneFieldChecker;
import org.example.qr.resolver.BaseCodeResolver;
import org.example.qr.resolver.BaseFieldResolver;
import org.example.qr.resolver.DefaultAtLeastOneFieldChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商户账户信息的QR码解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 3:59 下午
 */
@Component
public class MerchantAccountInfoCodeResolver extends BaseCodeResolver<MerchantAccountInfo> {

    private List merchantAccountInfoFieldResolvers;

    @Autowired
    public MerchantAccountInfoCodeResolver(List<MerchantAccountInfoFieldResolver> merchantAccountInfoFieldResolvers) {
        this.merchantAccountInfoFieldResolvers = merchantAccountInfoFieldResolvers;
    }

    @Override
    protected MerchantAccountInfo buildResolveResult(String qrCode) {
        return new MerchantAccountInfo();
    }

    @Override
    protected List<BaseFieldResolver> getFieldResolvers() {
        return merchantAccountInfoFieldResolvers;
    }

    @Override
    protected BaseAtLeastOneFieldChecker buildAtLeastOneFieldChecker() {
        return new DefaultAtLeastOneFieldChecker();
    }
}
