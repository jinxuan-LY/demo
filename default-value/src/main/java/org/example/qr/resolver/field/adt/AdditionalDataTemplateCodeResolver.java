package org.example.qr.resolver.field.adt;

import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.BaseAtLeastOneFieldChecker;
import org.example.qr.resolver.BaseCodeResolver;
import org.example.qr.resolver.BaseFieldResolver;
import org.example.qr.resolver.DefaultAtLeastOneFieldChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 额外字段模板-QR码解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 8:12 下午
 */
@Component
public class AdditionalDataTemplateCodeResolver extends BaseCodeResolver<QRCodeResult.AdditionalDataFieldTemplate> {

    private List additionalDataTemplateFieldResolvers;

    @Autowired
    public AdditionalDataTemplateCodeResolver(List<AdditionalDataTemplateFieldResolver> additionalDataTemplateFieldResolvers) {
        this.additionalDataTemplateFieldResolvers = additionalDataTemplateFieldResolvers;
    }

    @Override
    protected QRCodeResult.AdditionalDataFieldTemplate buildResolveResult(String qrCode) {
        return new QRCodeResult.AdditionalDataFieldTemplate();
    }

    @Override
    protected List<BaseFieldResolver> getFieldResolvers() {
        return additionalDataTemplateFieldResolvers;
    }

    @Override
    protected BaseAtLeastOneFieldChecker buildAtLeastOneFieldChecker() {
        return new DefaultAtLeastOneFieldChecker();
    }
}
