package com.example.shopbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Oder ID tối thiểu phải lớn hơn 0")
    private Long orderID;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product ID tối thiểu phải lớn hơn 0")
    private Long productID;

    @Min(value = 0, message = "Price tối thiểu phải lớn hơn hoặc bằng 0")
    private Long price;

    @JsonProperty("number_of_product")
    @Min(value = 1, message = "Number Of Product tối thiểu phải lớn hơn 0")
    private int numberOfProduct;

    @JsonProperty("total_money")
    @Min(value = 1, message = "Oder ID tối thiểu phải lớn hơn 0")
    private int totalMoney;
}
