package org.example.qr;

import com.shopee.bcf.common.exception.BaseException;
import lombok.Getter;

import java.util.Map;

/**
 * @author liekaihuang
 * @date 2020/6/11
 */
@Getter
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    private Map<String, String> fillParamMap;
    
    /**
     * 异常情况下返回给前端的data.
     */
    private Object data;

    public BizException(BizErrorType bizErrorType) {
        super(bizErrorType);
    }

    public BizException(BizErrorType bizErrorType, Map<String, String> fillParamMap) {
        super(bizErrorType);
        this.fillParamMap = fillParamMap;
    }

    public BizException(BizErrorType bizErrorType, String message) {
        super(bizErrorType, message);
    }
    
    public BizException(BizErrorType bizErrorType, Object data) {
        super(bizErrorType);
        this.data = data;
    }
    
    public BizException(BizErrorType bizErrorType, Map<String, String> fillParamMap, Object data) {
        super(bizErrorType);
        this.fillParamMap = fillParamMap;
        this.data = data;
    }
    
}
