package org.example.qr.resolver.field.adt;

import org.example.qr.bo.QRCodeResult.AdditionalDataFieldTemplate;
import org.springframework.stereotype.Component;

/**
 * 商户终端标识-字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/11 3:48 下午
 */
@Component
public class TerminalLabelFieldResolver implements AdditionalDataTemplateFieldResolver {

    public static final int TERMINAL_LENGTH = 16;

    @Override
    public String[] getIDs() {
        return new String[]{"07"};
    }

    @Override
    public boolean mandatory() {
        return false;
    }

    @Override
    public boolean checkLength(int length) {
        return length >= 1 && length <= 25;
    }

    @Override
    public void checkAndResolveValue(String fieldId, String length, String value, AdditionalDataFieldTemplate result) {
        result.setTerminalLabel(value.length() > TERMINAL_LENGTH ? value.substring(value.length() - TERMINAL_LENGTH) : value);
    }

}
