package org.example.qr;

import lombok.Getter;

/**
 * QR初始化方式
 *
 * @author:guyang.huang
 * @date:2022/2/12 4:33 下午
 */
@Getter
public enum QRInitiationMethod {

    /**
     * 静态
     */
    STATIC("11"),
    /**
     * 动态
     */
    DYNAMIC("12"),
    ;

    private String value;

    private QRInitiationMethod(String value) {
        this.value = value;
    }

    /**
     * 获取QR初始化方式
     *
     * @param value
     * @return
     */
    public static QRInitiationMethod getQRInitiationMethod(String value) {
        if (STATIC.value.equals(value)) {
            return STATIC;
        } else if (DYNAMIC.value.equals(value)) {
            return DYNAMIC;
        }
        return null;
    }
}
