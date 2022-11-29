package org.example.qr;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum TranStatus {

    /**
     * 初始化状态
     */
    INIT_WIAT_PROCESS(10, "待处理", "O", new AbstractCheckOrdertTransformation() {

        @Override
        public TranStatus[] getTransferOrderStatuses() {
            return new TranStatus[]{IN_PROCESSING, SUCCESS, FAILED};
        }

    }),

    /**
     * 处理中
     */
    IN_PROCESSING(20, "处理中", "O", new AbstractCheckOrdertTransformation() {

        @Override
        public TranStatus[] getTransferOrderStatuses() {
            return new TranStatus[]{SUCCESS, FAILED};
        }

    }),

    /**
     * 成功
     */
    SUCCESS(30, "成功", "S", new AbstractCheckOrdertTransformation() {

        @Override
        public TranStatus[] getTransferOrderStatuses() {
            return new TranStatus[]{};
        }

    }),

    /**
     * 失败
     */
    FAILED(40, "失败", "F", new AbstractCheckOrdertTransformation() {

        @Override
        public TranStatus[] getTransferOrderStatuses() {
            return new TranStatus[]{};
        }

    }),

    ;
	
    private int status;

    private String desc;

    private String paymentStatus;

    private AbstractCheckOrdertTransformation abstractCheckOrdertTransformation;

    private TranStatus(int status, String desc, String paymentStatus,
                       AbstractCheckOrdertTransformation abstractCheckOrdertTransformation) {
        this.status = status;
        this.desc = desc;
        this.paymentStatus = paymentStatus;
        this.abstractCheckOrdertTransformation = abstractCheckOrdertTransformation;
    }

    private static Map<Integer, TranStatus> STATUS_MAP = new HashMap<Integer, TranStatus>();

    static {
        for (TranStatus orderStatus : TranStatus.values()) {
            STATUS_MAP.put(orderStatus.getStatus(), orderStatus);
        }
    }

    public static TranStatus fromStatus(Integer status) {
        return STATUS_MAP.get(status);
    }

    @Slf4j
    public static abstract class AbstractCheckOrdertTransformation {

        /**
         * 获取可以转换的状态列表.
         * 
         * @return 可以转换的状态列表.
         */
        public abstract TranStatus[] getTransferOrderStatuses();
//
//        /**
//         * 检查订单状态转移.
//         *
//         * @param tranId                转账订单ID.
//         * @param toTransferOrderStatus toTransferOrderStatus.
//         */
//        public void checkOrderTransformation(String tranId, TranStatus toTransferOrderStatus) {
//            TranStatus[] transferOrderStatuses = getTransferOrderStatuses();
//            if (ArrayUtils.isEmpty(transferOrderStatuses)) {
//                log.warn("TranId:{} is now final status.Not allowed to be changed to :{}.",
//                        tranId, toTransferOrderStatus);
//                throw new BizException(BizErrorType.ORDER_STATE_MACHINE_ERROR);
//            }
//            if (ArrayUtils.contains(transferOrderStatuses, toTransferOrderStatus)) {
//                return;
//            }
//            log.warn("TranId:{} is not allowed to be changed to :{}.",
//                    tranId, toTransferOrderStatus);
//            throw new BizException(BizErrorType.ORDER_STATE_MACHINE_ERROR);
//        }

    }

}
