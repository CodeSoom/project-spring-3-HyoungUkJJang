package com.rent.rentshop.rent.repository;

import com.rent.rentshop.rent.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    Rent save(Rent rent);

}
