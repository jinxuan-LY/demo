package org.example.qr;

import com.shopee.bcf.common.exception.ErrorType;
import lombok.Getter;

/**
 * @author liekaihuang
 * @date 2020/6/11
 */
@Getter
public enum BizErrorType implements ErrorType {

    // common 00
    PARM_EMPTY(3070001, "上送参数缺失"),

    UID_IS_EMPTY(3070002, "参数uid不允许为空"),

    INVALID_PARAM(3070003, "参数非法"),

    TOKEN_EXPIRED(3071701,"token expired"),

    TOKEN_VERIFICATION_FAILED(3071702,"token verification failed"),

    SIGNATURE_INVALID(3071703,"Signature invalid"),

    INVALID_CLIENT_MESSAGE(3071704,"invalid client id or client secret"),

    INVALID_OAUTH_GRANT_TYPE(3071705,"invalid oauth grant type"),

    ACCOUNT_NOT_FOUND(4070001, "未找到账户"),

    GET_ACCOUNT_FAIL(4070002, "获取账户信息失败"),

    USER_NOT_OPEN_ACCOUNT(4070003, "用户未开户"),
    
    USER_CENTER_AUTH_DOUBLE_CHECK_RESULT_FAILED(4070004, "用户中心二次确认检查不通过"),

    ACCOUNT_IS_CLOSED(4070005, "account is closed"),

	ORDER_STATE_MACHINE_ERROR(4070006, "Order state machine error"),

	DB_OPEATE_EXCEPTION(4070007, "DB operate exception"),

    DOWN_STREAM_RETURN_ERROR(4070008, "down stream return not as expected"),

    CONFIRM_USE_QUOTA_ERROR(4070009,"confirm use quota error"),

    // transfer  01
    PARAM_NOT_MATCHED(3070101, "上送参数与服务端信息不匹配"),
    
    PASSAGEWAY_NOT_IN_AVAILABLE_LIST(3070102, "passageway can't find in available passageway list"),
    
    TRANSFER_TOKEN_NOT_EXISTS(4070101, "transfer transaction not exists or expired"),
    
    TRANSFER_AMOUNT_EXCEED_SINGLE_LIMIT(4070102, "转账金额超过单笔限额"),

    TRANSFER_AMOUNT_EXCEED_DAY_LIMIT(4070103, "转账金额超过单日限额"),

    TRANSFER_ORDER_NOT_EXIST(4070104, "转账订单不存在"),

    TRANSFER_AMOUNT_LESS_THAN_SINGLE_MINIMUM_LIMIT(4070105, "转账金额低于单笔最低限额"),

    ACCOUNT_BALANCE_INSUFFICIENT(4070106, "account blanace insufficient"),
    
    USER_NOT_FOUND_SETTLEMENT_ACCOUNT(4070107, "can't find settlement account"),
    
    PAYMENT_PASSAGEWAY_DISABLED(4070108, "current payment passageway disabled"),

    PAYMENT_PASSAGEWAY_NOT_EXIST(4070109, "current payment passageway not exist"),

    PAYMENT_PASSAGEWAY_ARGS_ERROR(4070110, "payment passageway args illegal"),

    PAYMENT_PASSAGEWAY_ALREADY_EXIST(4070111, "current payment passageway already exist"),

    TRANSFER_AMOUNT_LESS_THAN_SINGLE_MINIMUM_LIMIT_V2(4070112, "转账金额低于单笔最低限额"),

    FORBIDDEN_TRANSFER_IN(4070113, "bank account is forbidden to transfer in"),

    FORBIDDEN_TRANSFER_OUT(4070114, "bank account is forbidden to transfer out"),

    BLANK_EWALLET_NAME(4070115, "blank ewallet name"),
    
    SDK_TRANSFER_NOT_AVAILABLE_TRANTYPE(4070117, "sdk transfer not available tranType"),

    TRANSFER_USE_QUOTA_FAIL(4070116, "transfer use quota fail"),



    // deposit  02

    SAVING_BALANCE_TOKEN_NOT_EXISTS(4070201, "token not exist or expire"),

    //WITHDRAW_FAIL(4070201, "存款提取失败"),

    //DEPOSIT_FAIL(4070002, "活期存款失败"),

    // limit 03
    SINGLE_LIMIT_TOO_LOW(3070301, "单笔限额过低"),

    SINGLE_LIMIT_TOO_HIGH(3070302, "单笔限额过高"),

    DAY_LIMIT_TOO_HIGH(3070303, "日累计限额过高"),

    BANK_LIMIT_RESPONSE_INSUFFICIENT(3070304, "银行限额返回不充分"),

    SINGLE_LIMIT_EXCEED_DAY_LIMIT(3070305, "单笔限额高于日总限额"),

    DAY_LIMIT_LOWER_THAN_MIN_SINGLE_LIMIT(3070306, "单日限额低于单笔最低限额"),

    DAY_LIMIT_LOWER_THAN_SINGLE_LIMIT(3070307, "单日限额低于客户单笔限额"),

    SINGLE_LIMIT_TOO_LOW_V2(3070308, "单笔限额过低"),


    LIMIT_TOKEN_NOT_EXISTS(4070301, "限额token不存在或已过期"),

    LIMIT_NOT_SET(4070302, "用户未设置限额"),

    // payee 04
    INVALID_ACCOUNT(3070401, "行内账户不合法"),

    INVALID_PHONE(3070402,"phone number is invalid"),

    CONNT_ADD_SELF(4070401, "不能添加自己为转账人"),

    PAYEE_ADDING_FAIL(4070402,"添加收款人列表失败"),

    PAYEE_EXISTS(4070403,"收款人已存在列表"),

    PAYEE_TOKEN_NOT_EXISTS(4070404, "收款人token不存在或已过期"),
    //delete payeeId
    DELETE_PAYEE_FAIL(4070405, "delete payeeId fail"),

    CONTACT_PHONE_NO_LONGER_VALID(4070406, "contact phone no longer valid"),

    FAVORITE_PAYEE_FAIL(4070407, "favorite payee fail"),

    // query 05
    TRANSFER_ORDER_NOT_FOUND(4070501,"转账订单不存在"),

    ACCOUNT_NOT_MATCH_MOBILE(4070502,"输入的账号和手机号不匹配"),

    PERIOD_TOO_LONG(4070503,"Transaction period selected can not be more than 7 days"),

    TOO_MANY_RESULT(4070504,"Too many result!Please refine your search criteria!"),

    PARAM_ALL_EMPTY(4070505,"Please input at least one of these field!"),

    ACCOUNT_CLOSED(4070506,"this payee account has been closed"),

    // va
    INTRA_VA_INVALID_ACCOUNT(3070601, "invalid intra va"),
    
    INTRA_VA_TRAN_AMOUNT_NOT_MATCHED(3070602, "intra va tran amount not matched"),

    INCORRECT_MOBILE_FORMAT(3070603, "invalid mobile number"),

	// DD 07
	DD_TOKEN_TIMEOUT(4070701, "DD token timeout"),
	DD_THRESHOLD_AMOUNT_LIMIT_TOO_HIGH(4070702, "DD threshold amount is too high"),
	DD_TOKEN_CHECK_ERROR(4070703, "check DD token error"),
	DD_CUSTOMER_DAILY_LIMIT_TOO_HIGH(4070704, "dd customer daily limit is too high"),
	DD_CUSTOMER_DAILY_LIMIT_TOO_LOW(4070705, "dd customer daily limit is too low"),
	UNBIND_DD_TOKEN_NOT_EXISTS(4070706, "unbindDD token不存在或已过期"),
	DD_LIMIT_TOKEN_NOT_EXISTS(4070707, "dd限额token不存在或已过期"),
	DD_THRESHOLD_AMOUNT_LIMIT_TOO_LOW(4070708, "DD threshold amount is too low"),
	DD_AUTO_TOP_UP_AMOUNT_LIMIT_TOO_HIGH(4070709, "DD auto top-up amount is too high"),
	DD_AUTO_TOP_UP_AMOUNT_LIMIT_TOO_LOW(4070710, "DD auto top-up amount is too low"),
    UNBIND_DD_TOKEN_NOT_MATCH(4070711,"DD unbind token info not match"),
    DD_OTP_TOKEN_NOT_EXISTS(4070712, "dd pay token not exists"),
    DD_CUSTOMER_DAILY_LIMIT_TOO_HIGH_V2(4070713, "dd customer daily limit is too high"),
    DD_CUSTOMER_DAILY_LIMIT_TOO_LOW_V2(4070714, "dd customer daily limit is too low"),
    DD_PAY_PARAM_INVALID(4070715,"dd pay param invalid"),
    DD_PAY_ORDER_NOT_FOUND(4070716,"dd pay order not found"),
    DD_PAY_ORDER_EXPIRED(4070717,"dd pay order expired"),
    DD_GENERATE_TOKEN_PARAM_ERROR(4070718,"dd generate token param error"),
    DD_UNBIND_PARAM_ERROR(4070719,"dd unbind param error"),
    DD_QUERY_BIND_STATUS_PARAM_ERROR(4070720,"dd query bind status param error"),
    DD_REFUND_PARAM_ERROR(4070721,"dd refund param error"),
    DD_REFUND_ORDER_STATUS_INVALID(4070722,"dd refund order status invalid"),
    DD_PAY_ORDER_TOO_MUCH(4070723,"dd pay order too much"),
    DD_REFUND_STATUS_ERROR(4070724,"dd refund status error"),
    DD_ORDER_QUERY_PARAM_ERROR(4070725,"dd order query param error"),
    DD_ORDER_QUERY_UNKNOW_TYPE(4070726,"dd order query unknow type"),

    //26-34其他市场已用，为保持一致，从35开始
    /**
     * 转出余额不足
     */
    DD_INSUFFICIENT_TRANSFER_OUT_BALANCE(4070735, "insufficient transfer out balance"),

    /**
     * 转出账户状态不可用
     */
    ABNORMAL_TRANSFER_OUT_STATUS(4070736, "abnormal transfer out status"),

    /**
     * signature field error  08
     */
    ILLEGAL_SIGN_FIELD(4070801,"Illegal signature field"),
    ILLEGAL_CHECKCODE_EMPTY(4070802,"Illegal checkcode empty"),
    ILLEGAL_SIGN(4070803,"Illegal signature"),
    ILLEGAL_TICKET_ID(4070804,"Illegal ticketId"),
    ILLEGAL_CHECKCODE_ERR(4070805,"Illegal checkcode IO error"),

    /**
     * e statement 09
     */
    VIEW_ESTATEMENT_ERROR(4070901,"e-statement download error"),

    /**
     * view estatement 反欺诈token不存在
     */
    VIEW_ESTATEMENT_TOKEN_NOT_EXISTS(4070902, "view estatement token不存在或已过期"),
    /**
     * view Estatement 反欺诈token信息不匹配
     */
    VIEW_ESTATEMENT_TOKEN_NOT_MATCH(4070903,"view estatement token info not match"),

    /**
     * view estatement download token不存在
     */
    VIEW_ESTATEMENT_DOWNLOAD_TOKEN_NOT_EXISTS(4070904, "view estatement download token不存在或已过期"),

    /**
     * view estatement download token信息不匹配
     */
    VIEW_ESTATEMENT_DOWNLOAD_TOKEN_NOT_MATCH(4070905,"view estatement download token info not match"),
    
    // time deposit 10
    
    TD_TOKEN_NOT_EXISTS(4071001, "time deposit token not exists or expired"),
    TD_ORDER_NOT_EXIST(4071002, "TD tran order is not exist"),

    /**
     * 定期存款账户不存在.
     */
    TD_ACCOUNT_NOT_EXISTS(3107001, "could not find account"),

    /**
     * 修改转存方式token不存在
     */
    UPDATE_ROLLOVER_TOKEN_NOT_EXISTS(4071002, "update rollover token不存在或已过期"),

    /**
     * 转存方式未修改
     */
    ROLLOVER_TYPE_NOT_CHANGE(4071003, "rollover type is not changed"),

    /**
     * 无可购买的定存
     */
    NONE_TD_TO_BUY(4071004, "none td product to buy"),
    /**
     * 购买td的金额过小
     */
    TD_LITTLE_AMOUNT(4071005, "buy td amount is too little"),

	/**
	 * TD订单不存在
	 */
	TIME_DEPOSIT_ORDER_NOT_EXISTED(4071011, "time deposit order is not existed"),

    TIME_DEPOSIT_REQUEST_TIME_OUT(4071012,"request time out"),
	
	
    /**
     * 商户提现(merchant withdraw) 11
     */
    MERCHANT_ORDER_SIGN_VERIFY_FAIL(4071101, "sign invalid"),
    MERCHANT_WITHDRAW_INVALID_PARAM(4071102, "param invalid"),
    // 风险识别返回需要验证
    MERCHANT_WITHDRAW_AUTH_VERIFY_NEED_AUTH(4071103, "auth no pass"),
    // 风险识别调用失败
    MERCHANT_WITHDRAW_AUTH_VERIFY_FAIL(4071104, "auth fail"),

    MERCHANT_WITHDRAW_ACCOUNT_QUERY_NOT_ACTIVE(4071105, "account invalid"),
    MERCHANT_WITHDRAW_ACCOUNT_QUERY_FAIL(4071106, "account query fail"),

    MERCHANT_ORDER_NOT_EXISTS(4071107, "merchant withdraw order not exists"),
    
    DISABLE_MERCHANT_WITHDRAW(4071108, "merchant withdrawal function been disabled"),
    
    // shopeePay整合 12
    SHOPEEPAY_LINKAGE_TOKEN_NOT_EXISTS(4071201, "shopeePay linkage token not exists"),
    CONTRACT_ID_LINK_ERROR(4071202, "contractId link error"),
    BINDINGID_STATUS_NOT_ACTIVE(4071203, "bindingId status is not active"),

//    SHOPEEPAY_LINKAGE_ACQUIRING_TOKEN_NOT_EXISTS(4071204, "shopeePay linkage token not exists"),
//    SHOPEEPAY_LINKAGE_ACQUIRING_TOKEN_EXPIRED(4071205, "shopeePay linkage token expired"),
//    SHOPEEPAY_LINKAGE_ACQUIRING_TOKEN_INVALID(4071206, "shopeePay linkage token invalid"),
    SHOPEEPAY_LINKAGE_UC_BINDING_EXIST(4071207, "shopeePay linkage bindingId exist"),
    SHOPEEPAY_LINKAGE_UC_BINDING_NOT_EXIST(4071208, "shopeePay linkage binding not exist"),
    UNBIND_SHOPEELINKAGE_TOKEN_NOT_EXIST(4071209, "unbind shopeelinkage token not exist"),
    SHOPEEPAY_LINKAGE_STATUS_QUERY_PARAM_ERROR(4071210, "shopeePay linkage status query param error"),
    UNBIND_SHOPEELINKAGE_FAILED(4071211, "unbind shopeelinkage failed"),
    SHOPEE_PHONE_NOT_EQUALS_BANK_PHONE(4071212, "shopee phone is not equals to bank phone"),
    //	CURRENT_PHONE_NOT_EQUALS_LINKAGE_PHONE(4071213, "current phone is not equals to linkage phone"),
//	DEVICE_CHANGED(4071214, "device changed"),
    UNBIND_SHOPEELINKAGE_BINGDING_NOT_EXIST(4071215, "bingding Id not exist when unbind shopeelinkage"),
    BIND_TYPE_BLANK(4071216, "bindType can't blank"),
    BIND_TYPE_UNKNOWN(4071217, "unknown bindType"),
    
    BANK_API_SHOEPPUID_WHITELIST_VERIFY_FAIL(4071218, "shopeeUid is not in the whitelist"),

	// Digital Product 13
	DP_ORDER_STATUS_ERROR(4071301, "DP order status error"),
	DP_TRAN_AMOUNT_ERROR(4071302, "transaction amount error"),
	DP_SOURCE_ORDER_ERROR(4071303, "source order error"),
	DP_BC_ORDER_STATUS_ERROR(4071304, "BC order status error"),
	DP_BC_ORDER_NOT_EXISTED(4071305, "BC order not existed"),

    // QR 14
    QR_CODE_ILLEGAL(4071401, "QR code illegal"),
    QR_QUERY_MERCHANT_ACCOUNT_FAILED(4071402, "failed to query merchant account"),
    QR_CURRENCY_ILLEGAL(4071403, "Transaction currency illegal"),
    QR_TOKEN_NOT_EXISTED(4071404, "Haven't scan QR code or timeout"),
    QR_AMOUNT_ERROR(4071405, "amount error"),
    QR_OUT_OF_RANGE_OF_PASSAGEWAY_LIMIT(4071406, "out of range of passageway limit"),

    // reward withdraw 15
    REWARD_WITHDRAW_INVALID_PARAM(4071501, "param invalid"),
    REWARD_WITHDRAW_AMOUNT_ERROR(4071502, "reward withdraw amount error"),
    REWARD_WITHDRAW_STATUS_ERROR(4071503, "reward withdraw status error"),
    REWARD_WITHDRAW_RETURN_EMPTY_ERROR(4071504, "reward withdraw enturn empty error"),




    // BI fast 15
    BI_FAST_INVALID_USER(4071501, "user invalid in uc"),
    BI_FAST_INVALID_PHONE(4071602, "invalid phone number"),
    BI_FAST_TOKEN_NOT_EXISTED(4071603, "bifast verify timeout"),
    BI_FAST_COMMON_QUERY_FAIL(4071604, "bifast common query fail"),
    BI_FAST_INVALID_INTRA_ACCT(4071605, "core and pay acct inconsistent"),

    // Cash 17
    QUERY_TXN_SINGLLE_LIMIT_FAILED(4071701, "query acquiring system txn single limit failed"),
    CASHOUT_DAY_LIMIT_TOO_HIGH(4071702, "cashout day limit is too high"),
    CASHOUT_DAY_LIMIT_TOO_LOWER(4071703, "cashout day limit is too lower"),
    LIMIT_CONFIG_TOKEN_NOT_EXISTS(4071704, "set cashout day limit verify timeout"),
    CASH_ORDER_TOKEN_NOT_EXISTS(4071705, "cash token not exists"),
    CASH_ORDER_TOKEN_NOT_MATCH(4071706, "cash token not match"),
    GENERATE_WITHDRAW_TOKEN_ERROR(4071707, "generate withdraw token error"),
    CASH_PAYMENT_INIT_FAIL(4071708, "payment init fail"),
    CASH_MIN_UNIT_NOT_MATCH(4071709, "cash min unit not match"),
    QUERY_CASH_RESULT_FAILED(4071710, "query acquiring system txn result failed"),
    QUERY_ALGO_WITHDRAWAL_STATUS_FAILED(4071711, "query algo withdrawal status failed"),
    CASH_REVERSAL_FAILED(4071712, "cash reversal failed"),
    CASH_ORDER_NOT_EXISTS(4071713, "cash order not exists"),
    CASH_TOKEN_DECRYPT_ERROR(4071714, "cash order decrypt error"),
    CASH_UNIQUE_ERROR(4071715, "cash order unique error"),

    ALGO_REQUEST_ERROR(4071716,"algo service error"),

    ALGO_REQUEST_INVALID_TOKEN(4071717,"token is expired"),

    ALGO_WITHDRAWAL_UNIQUE_TRANSACTION(4071718,"Error unique transaction"),

    ALGO_REQUEST_PARAMS_NOT_MATCH(4071719,"algo request params not equals seabank params"),

    ACQUIRE_ALGO_ORDER_STATUS_ERROR(4071720,"cannot get algo order status"),

    ALGO_STATUS_NOT_CASH_ORDER_TARGET_STATUS(4071721,"algo tran status not the cash order target status"),

    ALGO_TO_BC_SIGNATURE_INVALID(4071722,"invalid signature"),

    ALGO_CLIENT_SECRET_APOLLO_NOT_CONFIG(4071723,"algo client secret apollo config is empty"),

    ALGO_REQUEST_PROCESS_ERROR(4071724,"algo request could not be processed correctly"),
    
    CASH_AMOUNT_EXCEED_SINGLE_LIMIT(4071725,"cash amount exceed single limit"),
    CASH_AMOUNT_LESS_THAN_SINGLE_MINIMUM_LIMIT(4071726,"cash amount less than single minimum limit"),
    CASH_AMOUNT_EXCEED_DAY_LIMIT(4071727, "cash amount exceed day limit"),
    ALGO_ACCESS_TOKEN_INVALID(4071728,"access token invalid"),

    GENERATE_DEPOSIT_TOKEN_ERROR(4071729, "generate deposit token error"),
    GENERATE_TOKEN_REACH_LIMIT(4071730, "generate token reach limit"),

    CASH_CONFIRM_USE_QUOTA_ERROR(4071731, "cash confirm use quota error")

    ;


    private int code;
    private String msg;

    BizErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
