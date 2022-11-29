package org.example.qr.resolver;

/**
 * 字段解析器接口
 *
 * @author:guyang.huang
 * @date:2022/2/9 4:43 下午
 */
public interface BaseFieldResolver<T> {

    /**
     * 获取字段ID
     *
     * @return
     */
    public String[] getIDs();

    /**
     * 字段是否必填
     *
     * @return
     */
    public boolean mandatory();

    /**
     * 检查字段长度是否正确
     *
     * @param length 字段长度
     * @return
     */
    public boolean checkLength(int length);

    /**
     * 校验并解析字段值，保存到QRCodeResult里面
     *
     * @param fieldId 字段ID
     * @param length  字段长度
     * @param value   字段值
     * @param result  解析结果
     */
    public void checkAndResolveValue(String fieldId, String length, String value, T result);
}
