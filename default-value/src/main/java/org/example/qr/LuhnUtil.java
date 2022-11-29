package org.example.qr;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Luhn校验工具
 *
 * @author:guyang.huang
 * @date:2022/2/11 5:18 下午
 */
@Slf4j
public class LuhnUtil {

    /**
     * 生成Luhn
     *
     * @param source 待生成的字符串，必须全数字
     * @return null表示生成失败
     */
    public static String generateLuhn(String source) {
        if (StringUtils.isBlank(source)) {
            log.warn("Blank source!");
            return null;
        }
        source = source + "0";
        Integer sum = checksum(source);
        if (sum == null) {
            log.warn("Illegal source:{}.");
            return null;
        }
        int result = 10 - (sum % 10);
        result = result == 10 ? 0 : result;
        return String.valueOf(result);
    }

    /**
     * 校验Luhn
     *
     * @param source
     * @return
     */
    public static boolean checkLuhn(String source) {
        return checksum(source) % 10 == 0;
    }

    private static Integer checksum(String source) {
        int sum = 0;
        for (int i = 0; i < source.length(); i++) {
            char v = source.charAt(i);
            if (!Character.isDigit(v)) {
                return null;
            }
            int n = Integer.parseInt(String.valueOf(v));
            if ((source.length() - i) % 2 == 0) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
        }
        return sum;
    }

}
