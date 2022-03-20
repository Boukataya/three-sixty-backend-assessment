package com.threesixty.services.listing;

import com.threesixty.commons.enums.ListingState;
import com.threesixty.commons.request.ListingRequest;
import com.threesixty.commons.respons.ApiResponse;

import java.util.UUID;

public interface IlistingService {

    ApiResponse createListing(ListingRequest listingRequest, UUID dealerId);

    ApiResponse updateListing(UUID listingId, ListingRequest listingRequest);

    ApiResponse publishListing(UUID listingId);

    ApiResponse unPublishListing(UUID listingId);

    ApiResponse findListing(UUID dealerId, ListingState listingState);

}
