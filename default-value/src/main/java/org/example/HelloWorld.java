package org.example;

import org.example.qr.bo.QRCodeResult;
import org.example.qr.resolver.CommonCodeResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-09-03 18:46:00
 */

@Service
public class HelloWorld {

    @Resource
    private CommonCodeResolver qrCodeResolver;

    public void PrintHello() {
        QRCodeResult qrCodeResult = qrCodeResolver.resolveQRCode(qrCode);
        System.out.println(qrCodeResult);
    }

    private static final String qrCode = "00020101021126670018ID.CO.EXAMPLE2.WWW01159360000901234560215MIDCONTOH1234560303UMI5204123453033605502015802ID5914NamaMerchantC76009NamaKota16110123456789062070703K19630497EF";
}
