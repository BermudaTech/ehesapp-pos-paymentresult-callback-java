package pos.example.paymentcallback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.MessageDigest;
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PaymentCallbackController {

	@PostMapping(value = "/callback")
	public void callback(@RequestParam("PaymentResult") String paymentResult) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] decodedBytes = Base64.getDecoder().decode(paymentResult);
        String decodedString = new String(decodedBytes);

        ObjectMapper mapper = new ObjectMapper();
        PaymentResultModel paymentResultModel = mapper.readValue(decodedString, PaymentResultModel.class);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        StringBuilder hashModel = new StringBuilder();
        hashModel.append(hashValueOf(paymentResultModel.sessionId));
        hashModel.append(hashValueOf(paymentResultModel.result));
        hashModel.append(hashValueOf(decimalFormat.format(paymentResultModel.paidPrice)));
        hashModel.append(hashValueOf(paymentResultModel.currency));
        hashModel.append(hashValueOf(paymentResultModel.returnCode));
        hashModel.append(hashValueOf(paymentResultModel.errCode));
        hashModel.append(hashValueOf(paymentResultModel.errMessage));
        hashModel.append(hashValueOf(paymentResultModel.mdStatus));
        hashModel.append(hashValueOf(paymentResultModel.groupId));
        hashModel.append(hashValueOf(paymentResultModel.transactionId));
        hashModel.append(hashValueOf(paymentResultModel.paymentOrderId));
        hashModel.append(hashValueOf(paymentResultModel.referenceNo));
        hashModel.append(hashValueOf(paymentResultModel.isThreeds));
        hashModel.append(hashValueOf(paymentResultModel.installment));
        hashModel.append(hashValueOf(paymentResultModel.bankCode));
        hashModel.append(hashValueOf(paymentResultModel.ip));
        hashModel.append(hashValueOf(paymentResultModel.terminalId));
        hashModel.append(hashValueOf(paymentResultModel.paymentMethodType));
        hashModel.append(hashValueOf(paymentResultModel.isDefaultPos));
        hashModel.append(hashValueOf(paymentResultModel.paymentCard.cardHolderName));
        hashModel.append(hashValueOf(paymentResultModel.paymentCard.cardNumber));
        hashModel.append(hashValueOf(paymentResultModel.paymentCard.expireYear));
        hashModel.append(hashValueOf(paymentResultModel.paymentCard.expireMonth));
        hashModel.append(hashValueOf(paymentResultModel.isCommercialCard));
        hashModel.append("kFa3KeznMnWykRyD");//burası config ile yönetilmeli.

        System.out.printf(hashModel.toString());

        String calculatedHash = generateSha1(hashModel.toString());

        System.out.printf(calculatedHash);

        if (paymentResultModel.result == true && paymentResultModel.hash.contentEquals(calculatedHash))
        {
            System.out.printf("ödeme başarılı");
        }else {
            System.out.printf("ödeme başarısız");
        }
	}

    private String generateSha1(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
        return DatatypeConverter.printBase64Binary(msdDigest.digest());
    }

    private String hashValueOf(Object obj) {
        String text = (obj == null) ? "" : obj.toString();
        if (text == "true" || text == "false"){
            text = text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
        }
        return text;
    }
}