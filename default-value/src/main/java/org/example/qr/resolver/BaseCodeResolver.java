package org.example.qr.resolver;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.qr.BizErrorType;
import org.example.qr.BizException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * QR码解析器父类
 *
 * @author:guyang.huang
 * @date:2022/2/8 1:33 下午
 */
@Slf4j
public abstract class BaseCodeResolver<T> {

    /**
     * 字段头固定长度，字段ID(2位)+字段长度值(2位)
     */
    public static final int FIELD_HEAD_LENGTH = 4;

    /**
     * 字段ID固定长度
     */
    public static final int FIELD_ID_LENGTH = 2;

    private Map<String, BaseFieldResolver> fieldResolverMap = new HashMap<>();

    /**
     * 必填字段的标记map，不要去修改
     */
    private Map<String, Boolean> MANDATORY_FIELDS_MAP = new HashMap<>();

    /**
     * 默认的字段解析器
     */
    @Resource
    private DefaultFieldResolver defaultFieldResolver;

    protected abstract List<BaseFieldResolver> getFieldResolvers();

    protected abstract T buildResolveResult(String qrCode);

    protected abstract BaseAtLeastOneFieldChecker buildAtLeastOneFieldChecker();

    @PostConstruct
    public void init() {
        List<BaseFieldResolver> fieldResolvers = getFieldResolvers();
        if (CollectionUtils.isEmpty(fieldResolvers)) {
            log.error("Empty fieldResolvers!");
            return;
        }
        for (BaseFieldResolver fieldResolver : fieldResolvers) {
            if (ArrayUtils.isEmpty(fieldResolver.getIDs())) {
                continue;
            }
            fieldResolverMap.putAll(Arrays.stream(fieldResolver.getIDs()).collect(Collectors.toMap(e -> e, e -> fieldResolver)));
        }
        //初始化必填字段的标记map
        MANDATORY_FIELDS_MAP = fieldResolverMap.entrySet().stream().filter(e -> e.getValue().mandatory())
                .collect(Collectors.toMap(e -> e.getKey(), e -> true));
    }

    /**
     * 解析并校验QR码
     *
     * @param qrCode QR码
     * @return QR码解析结果
     * @throws org.example.qr.BizException 校验失败抛出异常
     */
    public T resolveQRCode(String qrCode) {
        //复制必填字段标记map
        Map<String, Boolean> mandatoryFieldMaps = new HashMap<>(MANDATORY_FIELDS_MAP.size());
        mandatoryFieldMaps.putAll(MANDATORY_FIELDS_MAP);

        BaseAtLeastOneFieldChecker atLeastOneFieldChecker = buildAtLeastOneFieldChecker();

        //递归解析QR码
        T result = buildResolveResult(qrCode);
        resolve(qrCode, mandatoryFieldMaps, result, atLeastOneFieldChecker);

        //判断必填字段是否都存在
        if (MapUtils.isNotEmpty(mandatoryFieldMaps)) {
            log.warn("Mandatory IDs:{} can NOT found in QR code:{}.", mandatoryFieldMaps.keySet(), qrCode);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }

        //判断需要至少有一个字段的分组是否满足条件
        if (!atLeastOneFieldChecker.checkAll()) {
            log.warn("Group:{} can NOT found at least one ID in QR code:{}.", atLeastOneFieldChecker.getUnsatisfiedGroups(), qrCode);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
        return result;
    }

    /**
     * 解析字段
     *
     * @param qrCode                 QR码
     * @param mandatoryFieldMaps     必填字段的标记
     * @param result                 解析结果
     * @param atLeastOneFieldChecker 至少有一个字段检查器
     */
    private void resolve(String qrCode, Map<String, Boolean> mandatoryFieldMaps, T result, BaseAtLeastOneFieldChecker atLeastOneFieldChecker) {
        if (StringUtils.isBlank(qrCode) || qrCode.length() < FIELD_HEAD_LENGTH) {
            //QR码解析完毕，返回
            return;
        }

        //获取字段ID解析器
        String fieldId = qrCode.substring(0, FIELD_ID_LENGTH);
        BaseFieldResolver fieldResolver = fieldResolverMap.get(fieldId);
        if (fieldResolver == null) {
            fieldResolver = defaultFieldResolver;
        }

        //获取字段的长度
        String fieldLengthStr = qrCode.substring(FIELD_ID_LENGTH, FIELD_HEAD_LENGTH);
        if (!NumberUtils.isDigits(fieldLengthStr)) {
            log.warn("Invalid length digits in QR code:{}.", qrCode);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }
        Integer fieldLength = Integer.valueOf(fieldLengthStr);

        //检查字段的长度是否正确
        if (!fieldResolver.checkLength(fieldLength)) {
            log.warn("Invalid length in QR code:{}.", qrCode);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }

        //判断本字段实际长度是否超过QR码长度
        int fieldActualLength = FIELD_HEAD_LENGTH + fieldLength;
        if (qrCode.length() < fieldActualLength) {
            log.warn("QR code length:{} is shorter than field actual length:{}.QR code:{}.", qrCode.length(), fieldActualLength, qrCode);
            throw new BizException(BizErrorType.QR_CODE_ILLEGAL);
        }

        //读取字段的值，校验并解析
        String fieldValue = qrCode.substring(FIELD_HEAD_LENGTH, fieldActualLength);
        fieldResolver.checkAndResolveValue(fieldId, fieldLengthStr, fieldValue, result);

        //已完成解析则删除必填字段标记，并记录到需要至少有一个字段的分组里
        mandatoryFieldMaps.remove(fieldId);
        atLeastOneFieldChecker.record(fieldId);

        //解析下个字段
        resolve(qrCode.substring(fieldActualLength), mandatoryFieldMaps, result, atLeastOneFieldChecker);
    }
}
