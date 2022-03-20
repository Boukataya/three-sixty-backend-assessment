package com.threesixty.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Dealer implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID dealerId;
    private String name;
    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
    private List<Listing> listings;

    public Dealer(String name) {
        this.name = name;
    }

    public Dealer(UUID uuid, String james) {
    }

}
