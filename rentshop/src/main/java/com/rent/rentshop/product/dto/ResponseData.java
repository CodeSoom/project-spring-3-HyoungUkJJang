package com.rent.rentshop.product.dto;

import lombok.Data;

/**
 * 응답 전용
 * @param <T>
 */
@Data
public class ResponseData<T> {

    private T data;

    public ResponseData(T data) {
        this.data = data;
    }

}
