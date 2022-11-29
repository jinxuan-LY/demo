package org.example.qr;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * QRIS 小费类型
 */
@Getter
public enum QRTipTypeEnum {

    //用户自定义小费
    FREE_INPUT_MODE("01"),

    //固定小费
    FIXED_AMOUNT_MODE("02"),

    //按百分比算小费
    FIXED_PERCENTAGE_MODE("03"),

    ;

    private String value;

    QRTipTypeEnum(String value) {
        this.value = value;
    }

    private static Map<String, QRTipTypeEnum> QR_TIP_TYPE_MAP = new HashMap<>();

    static {
        for (QRTipTypeEnum tipType : QRTipTypeEnum.values()) {
            QR_TIP_TYPE_MAP.put(tipType.getValue(), tipType);
        }
    }

    /**
     * 获取QR初始化方式
     * @param type
     * @return
     */
    public static QRTipTypeEnum fromType(String type) {
        return QR_TIP_TYPE_MAP.get(type);
    }
}
