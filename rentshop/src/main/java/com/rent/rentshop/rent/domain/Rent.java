package com.rent.rentshop.rent.domain;

import com.rent.rentshop.member.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;

    protected Rent() {};

    @Builder
    public Rent(LocalDateTime rentalDate, LocalDateTime returnDate, RentStatus rentStatus) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.rentStatus = rentStatus;
    }



}
