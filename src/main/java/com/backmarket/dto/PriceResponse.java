package com.backmarket.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PriceResponse {

    private Integer status_int;
    private String status;
    private Integer price;

    /* Constructor */
    public PriceResponse(Integer status_int, String status, Integer price) {
        this.status_int = status_int;
        this.status = status;
        this.price = price;
    }

}
