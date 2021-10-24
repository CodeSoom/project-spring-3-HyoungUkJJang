package com.rent.rentshop.member.repository;

import com.rent.rentshop.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    boolean existsByUserEmail(String userEmail);

}
