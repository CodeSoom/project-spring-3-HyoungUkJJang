package com.rent.rentshop.rent.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.repository.ProductRepository;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.domain.RentStatus;
import com.rent.rentshop.rent.dto.RentRequest;
import com.rent.rentshop.rent.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentServiceImpl implements RentService{

    private final RentRepository rentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Rent createRent(String userId, Long productId, RentRequest rentRequest) {

        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException());

        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException());

        Rent rent = Rent.builder()
                .rentalDate(rentRequest.getRentalDate())
                .returnDate(rentRequest.getReturnDate())
                .rentStatus(RentStatus.WAIT)
                .build();
        rent.setUser(findUser);
        rent.setProduct(findProduct);

        return rentRepository.save(rent);

    }

}
