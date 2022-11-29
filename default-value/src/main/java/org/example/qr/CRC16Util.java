package org.example.qr;

/**
 * CRC16 ISO/IEC13239 工具类
 *
 * @author:guyang.huang
 * @date:2022/2/11 6:25 下午
 */
public class CRC16Util {

    private static final int POLYNOMIAL = 0x1021;
    private static final int PRESET_VALUE = 0xFFFF;

    /**
     * 生成CRC16 CCITT_FALSE校验码
     *
     * @param buffer
     * @return
     */
    public static String crc16_CCITT_FALSE(byte[] buffer) {
        int wCRCin = PRESET_VALUE;
        for (byte b : buffer) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((wCRCin >> 15 & 1) == 1);
                wCRCin <<= 1;
                if (c15 ^ bit) {
                    wCRCin ^= POLYNOMIAL;
                }
            }
        }
        wCRCin &= 0xffff;
        wCRCin ^= 0x0000;
        return String.format("%04X", wCRCin);
    }

}
