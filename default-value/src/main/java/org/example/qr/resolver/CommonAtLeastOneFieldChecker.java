package org.example.qr.resolver;

/**
 * 通用的至少一个字段检测器
 *
 * @author:guyang.huang
 * @date:2022/2/10 6:08 下午
 */
public class CommonAtLeastOneFieldChecker extends BaseAtLeastOneFieldChecker {

    @Override
    protected String getFieldIdToGroupsMapString() {
        return "{\"26\":[\"g1\"],\"27\":[\"g1\"],\"28\":[\"g1\"],\"29\":[\"g1\"],\"30\":[\"g1\"],\"31\":[\"g1\"],\"32\":[\"g1\"],\"33\":[\"g1\"],\"34\":[\"g1\"],\"35\":[\"g1\"],\"36\":[\"g1\"],\"37\":[\"g1\"],\"38\":[\"g1\"],\"39\":[\"g1\"],\"40\":[\"g1\"],\"41\":[\"g1\"],\"42\":[\"g1\"],\"43\":[\"g1\"],\"44\":[\"g1\"],\"45\":[\"g1\"],\"51\":[\"g1\"]}";
    }

    @Override
    protected String getMandatoryGroupMapString() {
        return "{\"g1\":true}";
    }
}
