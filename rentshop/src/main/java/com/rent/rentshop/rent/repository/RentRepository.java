package com.rent.rentshop.rent.repository;

import com.rent.rentshop.rent.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    Rent save(Rent rent);

    @Query("select r from Rent r join fetch r.product p join fetch r.user u where u.id = :userId")
    List<Rent> getMyRentList(@Param("userId") Long userId);

}
