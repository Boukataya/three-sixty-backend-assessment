package com.threesixty.commons.mapper;

import com.threesixty.models.Listing;
import com.threesixty.commons.respons.ListingResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static List<ListingResponse> map(List<Listing> listings) {
        return listings.stream().map(listing -> {
            ListingResponse listingResponse = new ListingResponse();
            listingResponse.setListingId(listing.getListingId());
            listingResponse.setPrice(listing.getPrice());
            listingResponse.setCreatedAt(dateFormat.format(listing.getCreatedAt()));
            listingResponse.setCreatedAt(dateFormat.format(listing.getCreatedAt()));
            listingResponse.setVehicle(listing.getVehicle());
            listingResponse.setListingState(listing.getListingState());
            return listingResponse;
        }).collect(Collectors.toList());
    }

}
