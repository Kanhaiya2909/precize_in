package com.precize.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String address;
    private String city;
    private String country;
    private String pincode;
    private int satScore;

    @Transient
    public boolean isPassed() {
        return satScore > 30;
    }

}


