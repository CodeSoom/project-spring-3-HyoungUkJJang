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
    public ResponseData createRental(@RequestParam("userEmail") String userEmail, @RequestParam("productId") Long productId,
                                     @RequestBody @Valid RentRequest rentRequest) {

        Rent rent = rentService.createRent(userEmail, productId, rentRequest);

        RentResponse result = RentResponse.builder()
                .rentId(rent.getId())
                .productName(rent.getProduct().getProductName())
                .rentalDate(rent.getRentalDate())
                .returnDate(rent.getRentalDate())
                .build();

        return new ResponseData(result);

    }

    @GetMapping("/myrental/{userEmail}")
    public List<Rent> getMyRental(@PathVariable("userEmail") String userEmail) {
        List<Rent> myRent = rentService.findMyRent(userEmail);
        return myRent;
    }

}
