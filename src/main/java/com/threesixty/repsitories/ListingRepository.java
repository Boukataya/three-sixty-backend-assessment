package com.threesixty.repsitories;

import com.threesixty.models.Listing;
import com.threesixty.commons.enums.ListingState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {

    Listing findByListingId(UUID listingId);

    List<Listing> findListingsByDealer_DealerIdAndListingState(UUID dealerId, ListingState listingState);

}
