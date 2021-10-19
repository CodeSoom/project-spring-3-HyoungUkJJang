package com.rent.rentshop.product.dto;

import lombok.Data;

@Data
public class ResponseData<T> {

    private T data;

    public ResponseData(T data) {
        this.data = data;
    }

}
