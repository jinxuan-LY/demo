package org.example.qr.resolver;

/**
 * 默认的至少一个字段检查器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:18 下午
 */
public class DefaultAtLeastOneFieldChecker extends BaseAtLeastOneFieldChecker {
    
    @Override
    protected String getFieldIdToGroupsMapString() {
        return null;
    }

    @Override
    protected String getMandatoryGroupMapString() {
        return null;
    }
}
