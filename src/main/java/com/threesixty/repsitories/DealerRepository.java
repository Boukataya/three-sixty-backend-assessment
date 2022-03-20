package com.threesixty.repsitories;

import com.threesixty.models.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DealerRepository extends JpaRepository<Dealer, UUID> {

    Dealer findByDealerId(UUID dealerId);

}
