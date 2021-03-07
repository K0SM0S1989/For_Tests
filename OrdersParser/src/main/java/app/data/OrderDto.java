package app.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    @JsonProperty("orderId")
    private Integer id;
    private String amount;
    private String currency;
    private String comment;




    public OrderDto(String[] inputData) {
        id = Integer.valueOf(inputData[0].trim());
        amount = inputData[1].trim();
        currency = inputData[2].trim();
        comment = inputData[3].trim();
    }

    public OrderDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
