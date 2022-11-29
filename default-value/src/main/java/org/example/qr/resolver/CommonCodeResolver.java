package org.example.qr.resolver;

import org.example.qr.bo.QRCodeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通用的QR码解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 5:34 下午
 */
@Component
public class CommonCodeResolver extends BaseCodeResolver<QRCodeResult> {

    private List commonFieldResolvers;

    @Autowired
    public CommonCodeResolver(List<CommonFieldResolver> commonFieldResolvers) {
        this.commonFieldResolvers = commonFieldResolvers;
    }

    @Override
    protected QRCodeResult buildResolveResult(String qrCode) {
        QRCodeResult result = new QRCodeResult();
        result.setWholeQRCode(qrCode);
        return result;
    }

    @Override
    protected List<BaseFieldResolver> getFieldResolvers() {
        return commonFieldResolvers;
    }

    @Override
    protected BaseAtLeastOneFieldChecker buildAtLeastOneFieldChecker() {
        return new CommonAtLeastOneFieldChecker();
    }
}
