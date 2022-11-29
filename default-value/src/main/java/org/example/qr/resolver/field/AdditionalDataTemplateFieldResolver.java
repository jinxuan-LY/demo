package org.example.qr.resolver.field;

import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonFieldResolver;
import org.example.qr.resolver.field.adt.AdditionalDataTemplateCodeResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 额外字段-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:39 下午
 */
@Component
public class AdditionalDataTemplateFieldResolver implements CommonFieldResolver {

    @Resource
    private AdditionalDataTemplateCodeResolver additionalDataTemplateCodeResolver;

    @Override
    public String[] getIDs() {
        return new String[]{"62"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length >= 1 && length <= 99;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, QRCodeResult result) {
        QRCodeResult.AdditionalDataFieldTemplate additionalDataFieldTemplate = additionalDataTemplateCodeResolver.resolveQRCode(value);
        result.setAdditionalDataFieldTemplate(additionalDataFieldTemplate);
        result.setWholeAdditionalDataFieldTemplate(fieldId + length + value);
    }
}
