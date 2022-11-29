package org.example.qr.bo;

import lombok.Data;
import org.example.qr.QRInitiationMethod;

import java.util.List;

/**
 * QR码解析结果
 *
 * @author:guyang.huang
 * @date:2022/2/8 1:36 下午
 */
@Data
public class QRCodeResult {

    /**
     * 完整的QR码
     */
    private String wholeQRCode;

    /**
     * QR初始化方式
     */
    private QRInitiationMethod qrInitiationMethod;

    /**
     * 交易金额
     */
    private String transactionAmount;

    /**
     * 商户类别编号，MCC
     */
    private String merchantCategoryCode;

    /**
     * 国家编码
     */
    private String countryCode;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户城市
     */
    private String merchantCity;

    /**
     * 邮递区号
     */
    private String postalCode;

    /**
     * 完整的邮递区号（ID+长度+邮递区号值）
     */
    private String wholePostalCode;

    /**
     * 商户账户信息列表
     */
    private List<MerchantAccountInfo> merchantAccounts;

    /**
     * 额外数据字段模板
     */
    private AdditionalDataFieldTemplate additionalDataFieldTemplate;

    /**
     * 完整的额外数据字段模板（ID+长度+额外数据字段模板值）
     */
    private String wholeAdditionalDataFieldTemplate;

    /**
     *  小费类型
     */
    private String tipType;

    /**
     *  小费类型02时必传
     */
    private String fixedTip;

    /**
     *  小费金额03时必传
     */
    private String percentageTip;

    /**
     *  预估小费金额
     */
    private String estimatedTipAmount;

    /**
     * 额外数据字段模板
     */
    @Data
    public static class AdditionalDataFieldTemplate {

        /**
         * 商户终端标识
         */
        private String terminalLabel;
    }

    /**
     * 商户账户信息
     */
    @Data
    public static class MerchantAccountInfo {

        /**
         * 商户标识MID
         */
        private String merchantId;

        /**
         * 商户账号MPAN
         */
        private String mpan;

        /**
         * National Numbering System of ID
         */
        private String nns;

        /**
         * 商户标准
         */
        private String merchantCriteria;

        /**
         * 是否为国家通用商户
         */
        private boolean nationalMerchant;

    }
}
