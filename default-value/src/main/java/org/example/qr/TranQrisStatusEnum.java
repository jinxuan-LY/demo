package org.example.qr;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * TranQris状态枚举
 */
@Getter
public enum TranQrisStatusEnum {

	/**
	 * 完成检查
	 */
	FINISH_CHECK(10, "O", new AbstractCheckOrderTransformation() {

		@Override
		public TranQrisStatusEnum[] getNextStatuses() {
			return new TranQrisStatusEnum[]{PENDING, SUCCESS, FAILED};
		}

	}),

	/**
	 * 交易中
	 */
	PENDING(20, "O", new AbstractCheckOrderTransformation() {

		@Override
		public TranQrisStatusEnum[] getNextStatuses() {
			return new TranQrisStatusEnum[]{SUCCESS, FAILED};
		}

	}),

	/**
	 * 交易成功
	 */
	SUCCESS(30, "S", new AbstractCheckOrderTransformation() {

		@Override
		public TranQrisStatusEnum[] getNextStatuses() {
			return new TranQrisStatusEnum[]{};
		}

	}),

	/**
	 * 交易失败
	 */
	FAILED(40, "F", new AbstractCheckOrderTransformation() {

		@Override
		public TranQrisStatusEnum[] getNextStatuses() {
			return new TranQrisStatusEnum[]{};
		}

	}),

	;

	private Short status;

	/**
	 * 对应的支付状态值
	 */
	private String paymentStatus;

	private AbstractCheckOrderTransformation orderTransformationChecker;

	private TranQrisStatusEnum(int status, String paymentStatus, AbstractCheckOrderTransformation orderTransformationChecker) {
		this.status = (short) status;
		this.paymentStatus = paymentStatus;
		this.orderTransformationChecker = orderTransformationChecker;
	}

	private static Map<Short, TranQrisStatusEnum> STATUS_MAP = new HashMap<Short, TranQrisStatusEnum>();

	static {
		for (TranQrisStatusEnum orderStatus : TranQrisStatusEnum.values()) {
			STATUS_MAP.put(orderStatus.getStatus(), orderStatus);
		}
	}

	public static TranQrisStatusEnum fromStatus(Short status) {
		return STATUS_MAP.get(status);
	}

	@Slf4j
	public static abstract class AbstractCheckOrderTransformation {

		/**
		 * 获取可转换的下一个状态列表.
		 *
		 * @return
		 */
		public abstract TranQrisStatusEnum[] getNextStatuses();
//
//		/**
//		 * 检查订单状态转移.
//		 *
//		 * @param nextStatus 下个状态值
//		 */
//		public void checkStatusTransformation(TranQrisStatusEnum nextStatus) {
//			TranQrisStatusEnum[] nextStatuses = getNextStatuses();
//			if (ArrayUtils.isEmpty(nextStatuses)) {
//				log.warn("TranQrisStatus:{} is the FINAL status, can not transform to {}.", this, nextStatus);
//				throw new BizException(BizErrorType.ORDER_STATE_MACHINE_ERROR);
//			}
//			if (!ArrayUtils.contains(nextStatuses, nextStatus)) {
//				log.warn("TranQrisStatus:{} can not transform to {}.", this, nextStatus);
//				throw new BizException(BizErrorType.ORDER_STATE_MACHINE_ERROR);
//			}
//		}

	}

}
