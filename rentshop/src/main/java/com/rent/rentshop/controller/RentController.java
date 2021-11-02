package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.dto.RentRequest;
import com.rent.rentshop.rent.dto.RentResponse;
import com.rent.rentshop.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rent/rental")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    public ResponseData createRental(@RequestParam("userId") String userId, @RequestParam("productId") Long productId,
                                     @RequestBody @Valid RentRequest rentRequest) {

        Rent rent = rentService.createRent(userId, productId, rentRequest);

        RentResponse result = RentResponse.builder()
                .rentId(rent.getId())
                .productName(rent.getProduct().getProductName())
                .rentalDate(rent.getRentalDate())
                .returnDate(rent.getRentalDate())
                .build();

        return new ResponseData(result);

    }

    @GetMapping("/myrental/{userId}")
    public List<Rent> getMyRental(@PathVariable("userId") String userId) {
        List<Rent> myRent = rentService.findMyRent(userId);
        return myRent;
    }

}
