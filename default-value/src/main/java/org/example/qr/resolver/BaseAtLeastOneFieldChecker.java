package org.example.qr.resolver;

import com.shopee.bcf.common.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 多个字段至少有一个存在-基类检查器
 *
 * @author:guyang.huang
 * @date:2022/2/9 9:18 下午
 */
public abstract class BaseAtLeastOneFieldChecker {

    private Map<String, List<String>> fieldIdToGroupsMap;

    private Map<String, Boolean> mandatoryGroupMap;

    /**
     * 获取字段ID到分组的映射配置
     *
     * @return
     */
    protected abstract String getFieldIdToGroupsMapString();

    /**
     * 获取需要至少一个ID的组的映射配置
     *
     * @return
     */
    protected abstract String getMandatoryGroupMapString();

    public BaseAtLeastOneFieldChecker() {
        String fieldIdToGroupsMapString = getFieldIdToGroupsMapString();
        fieldIdToGroupsMap = StringUtils.isBlank(fieldIdToGroupsMapString) ? Collections.emptyMap() : JacksonUtil.toMap(fieldIdToGroupsMapString);

        String mandatoryGroupMapString = getMandatoryGroupMapString();
        mandatoryGroupMap = StringUtils.isBlank(mandatoryGroupMapString) ? Collections.emptyMap() : JacksonUtil.toMap(mandatoryGroupMapString);
    }

    /**
     * 记录下已存在的ID
     *
     * @param fieldId
     */
    public void record(String fieldId) {
        if (fieldIdToGroupsMap.containsKey(fieldId)) {
            fieldIdToGroupsMap.get(fieldId).forEach(e -> {
                mandatoryGroupMap.remove(e);
            });
        }
    }

    /**
     * 检查每个组是否都至少有一个ID存在
     *
     * @return
     */
    public boolean checkAll() {
        return mandatoryGroupMap.isEmpty();
    }

    /**
     * 获取还未存在至少一个ID的组
     *
     * @return
     */
    public Map<String, Boolean> getUnsatisfiedGroups() {
        return mandatoryGroupMap;
    }
}
