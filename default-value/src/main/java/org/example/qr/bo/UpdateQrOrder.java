package org.example.qr.bo;

import lombok.Data;
import org.example.qr.TranQrisStatusEnum;
import org.example.qr.TranStatus;

/**
 * 更新QR订单状态BO
 *
 * @author:guyang.huang
 * @date:2022/2/13 12:26 上午
 */
@Data
public class UpdateQrOrder {

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 交易流水号
     */
    private String tranId;

    /**
     * 交易错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * TranOrder当前状态
     */
    private TranStatus toCurrentStatus;

    /**
     * TranOrder下一状态
     */
    private TranStatus toNextStatus;

    /**
     * TranQris当前状态
     */
    private TranQrisStatusEnum tqCurrentStatus;

    /**
     * TranQris下一状态
     */
    private TranQrisStatusEnum tqNextStatus;
}
