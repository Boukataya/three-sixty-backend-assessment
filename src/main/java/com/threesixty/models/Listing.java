package com.threesixty.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.threesixty.commons.enums.ListingState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Listing implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID listingId;
    private String vehicle;
    private double price;
    @CreationTimestamp
    private Date createdAt;
    @Enumerated(value = EnumType.STRING)
    private ListingState listingState;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dealer_id")
    @JsonIgnore
    private Dealer dealer;

    public Listing(String vehicle, double price, ListingState listingState, Dealer dealer) {
        this.vehicle = vehicle;
        this.price = price;
        this.listingState = listingState;
        this.dealer = dealer;
    }

}
