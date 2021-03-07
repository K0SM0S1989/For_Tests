package app.data;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "amount", "currency", "comment", "filename", "line", "result"})
public class Order{
    private final String ERROR_AMOUNT = "Неверный формат суммы";

    private Integer id;

    private Double amount;

    private String currency;

    private String comment;

    @JsonProperty("filename")
    private String fileName;

    private Integer line;

    private String result;

    public Order() {
    }

    public Order(OrderDto orderDto, int index, String fileName) {

        result = "OK";
        try {
            amount = Double.valueOf(orderDto.getAmount());
        }catch (NumberFormatException exception){
            amount = null;
            result = ERROR_AMOUNT;
        }
        id = orderDto.getId();
        currency = orderDto.getCurrency();
        comment = orderDto.getComment();
        this.fileName = fileName;
        line = index;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
