package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.dto.RentRequest;
import com.rent.rentshop.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rent/rental")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    public ResponseData createRental(@RequestHeader("userId") String userId, @RequestParam("productId") Long productId,
                                     @RequestBody @Valid RentRequest rentRequest) {

        Rent rent = rentService.createRent(userId, productId, rentRequest);

        return new ResponseData(rent);

    }

}
