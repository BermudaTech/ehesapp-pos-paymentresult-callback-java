package pos.example.paymentcallback;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentCardModel {
    @JsonProperty("CardHolderName") 
    public String cardHolderName;
    @JsonProperty("CardNumber") 
    public String cardNumber;
    @JsonProperty("ExpireYear") 
    public String expireYear;
    @JsonProperty("ExpireMonth") 
    public String expireMonth;
    @JsonProperty("Cvc") 
    public String cvc;
    @JsonProperty("CardToken") 
    public String cardToken; 
}