package com.rent.rentshop.rent.repository;

import com.rent.rentshop.rent.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    Rent save(Rent rent);

    @Query("select r from Rent r")
    List<Rent> getRentList(Long userId);

}
