package com.rent.rentshop.rent.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class RentRequest {

    @NotEmpty(message = "대여 시작 날짜는 필수 선택 값 입니다.")
    private LocalDateTime rentalDate;
    @NotEmpty(message = "대여 반납 날짜는 필수 선택 값 입니다.")
    private LocalDateTime returnDate;

}
