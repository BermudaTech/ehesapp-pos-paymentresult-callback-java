package pos.example.paymentcallback;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentResultModel{
    @JsonProperty("SessionId") 
    public String sessionId;
    @JsonProperty("Result") 
    public boolean result;
    @JsonProperty("PaidPrice") 
    public double paidPrice;
    @JsonProperty("Currency") 
    public String currency;
    @JsonProperty("Installment") 
    public int installment;
    @JsonProperty("BankCode") 
    public int bankCode;
    @JsonProperty("Ip") 
    public String ip;
    @JsonProperty("TerminalId") 
    public String terminalId;
    @JsonProperty("PaymentMethodType") 
    public String paymentMethodType;
    public boolean isCommercialCard;
    public boolean isDefaultPos;
    @JsonProperty("CreatedTime") 
    public Date createdTime;
    @JsonProperty("ReturnCode") 
    public String returnCode;
    @JsonProperty("ErrCode") 
    public String errCode;
    @JsonProperty("ErrMessage") 
    public String errMessage;
    @JsonProperty("GroupId") 
    public String groupId;
    @JsonProperty("TransactionId") 
    public String transactionId;
    @JsonProperty("PaymentOrderId") 
    public String paymentOrderId;
    @JsonProperty("ReferenceNo") 
    public String referenceNo;
    @JsonProperty("MdStatus") 
    public String mdStatus;
    @JsonProperty("Hash") 
    public String hash;
    @JsonProperty("IsThreeds") 
    public boolean isThreeds;
    @JsonProperty("PaymentCard") 
    public PaymentCardModel paymentCard;
    @JsonProperty("LogoUrl") 
    public String logoUrl;
    @JsonProperty("Retry") 
    public boolean retry;
    @JsonProperty("BankName") 
    public String bankName;
    @JsonProperty("SaveCardToken") 
    public String saveCardToken;
}