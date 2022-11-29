package org.example.qr.resolver;

import org.springframework.stereotype.Component;

/**
 * 默认的字段解析器
 *
 * @author:guyang.huang
 * @date:2022/2/9 10:24 下午
 */
@Component
public class DefaultFieldResolver implements BaseFieldResolver<Object> {

    @Override
    public String[] getIDs() {
        return new String[0];
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
    public void checkAndResolveValue(String fieldId, String length, String value, Object result) {

    }
}
