package com.rent.rentshop.rent.service;

import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.dto.RentRequest;

public interface RentService {

    Rent createRent(String userId, Long productId, RentRequest rentRequest);

}
