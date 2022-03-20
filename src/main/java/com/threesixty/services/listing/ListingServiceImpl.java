package com.threesixty.services.listing;

import com.threesixty.exception.DealerNotFoundException;
import com.threesixty.exception.ListingNotFoundException;
import com.threesixty.exception.TierLimitExceeption;
import com.threesixty.models.Dealer;
import com.threesixty.models.Listing;
import com.threesixty.repsitories.DealerRepository;
import com.threesixty.repsitories.ListingRepository;
import com.threesixty.commons.enums.ListingState;
import com.threesixty.commons.enums.ResponseStatus;
import com.threesixty.commons.mapper.Mapper;
import com.threesixty.commons.request.ListingRequest;
import com.threesixty.commons.respons.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListingServiceImpl implements IlistingService {

    private static final Logger logger = LogManager.getLogger(ListingServiceImpl.class);
    private final ListingRepository listingRepository;
    private final DealerRepository dealerRepository;
    @Value("${tier_limit}")
    private int tierLimit;
    // Injecting the dependency using thg the constructor
    public ListingServiceImpl(ListingRepository listingRepository, DealerRepository dealerRepository) {
        this.listingRepository = listingRepository;
        this.dealerRepository = dealerRepository;
    }

    @Override
    public ApiResponse createListing(ListingRequest listingRequest, UUID dealerId) {
        logger.info("Saving a new listing for dealer with UUID {}", dealerId);

        // Retrieving the dealer using the primary key
        Dealer dealer = dealerRepository.findByDealerId(dealerId);

        //Check if the dealer exist
        if (dealer == null) {
            logger.info("Dealer with UUID {} not found", dealerId.toString());
            //Throw the exception DealerNotFoundException
            throw new DealerNotFoundException("No dealer found with provided uuid");
        }
        // Create a new listing object
        Listing listing = new Listing(listingRequest.getVehicle(), listingRequest.getPrice(), ListingState.DRAFT, dealer);
        // Save the new listing using JPA
        listingRepository.save(listing);

        // Display the server response
        return new ApiResponse(listing, 200, "New listing was registered successfully", ResponseStatus.SUCCESS);
    }

    @Override
    public ApiResponse updateListing(UUID listingId, ListingRequest listingRequest) {
        // Retrieving the listing using the primary key
        Optional<Listing> listing = listingRepository.findById(listingId);
        //Check if the listing exist
        if (listing.isEmpty()) {
            logger.info("listing with id : {} not found", listingId);
            //Throw the exception ListingNotFoundException
            throw new ListingNotFoundException("No listing found with provided uuid");
        }
        // Create an object of the existing listing and update its properties with the new ones
        Listing oldListing = listing.get();
        Dealer dealer = oldListing.getDealer();
        dealerRepository.save(dealer);
        oldListing.setVehicle(listingRequest.getVehicle());
        oldListing.setPrice(listingRequest.getPrice());
        // Save the updated listing using JPA
        listingRepository.save(oldListing);
        // Display the server response
        return new ApiResponse(listing, 200, "Your listing was updated successfully", ResponseStatus.SUCCESS);
    }

    @Override
    public ApiResponse findListing(UUID dealerId, ListingState listingState) {
        // Retrieving all dealer listings with specific status using spring data jpa method conventions for query
        List<Listing> listings = listingRepository.findListingsByDealer_DealerIdAndListingState(dealerId, listingState);
        // Check if any listing found
        if (listings.isEmpty()) {
            // Display a message of no listing found
            return new ApiResponse(Mapper.map(listings), 200, "No listing found", ResponseStatus.SUCCESS);
        }
        // Display the requested listings
        return new ApiResponse(Mapper.map(listings), 200, "", ResponseStatus.SUCCESS);
    }

    @Override
    public ApiResponse publishListing(UUID listingId) {
        // Retrieving the listing using the primary key
        Optional<Listing> listing = listingRepository.findById(listingId);
        // Check if the listing exist
        if (listing.isPresent()) {
            logger.info("Listing with id : {} exist", listingId);
            Listing selectedListing = listing.get();

            // Retrieve all the dealer listings that are published
            List<Listing> dealerPublishedListing = listingRepository.findListingsByDealer_DealerIdAndListingState(selectedListing.getDealer().getDealerId(), ListingState.PUBLISHED);
            logger.info("Tier limit = {}", tierLimit);
            logger.info("Dealer published listings = {}", dealerPublishedListing.size());
            // Check if the number of published listing equal the tier limit
            if (dealerPublishedListing.size() == tierLimit) {
                logger.info("Dealer reached the limit of published limit allowed");
                throw new TierLimitExceeption("Tier limit exceeded!");
            }
            // Change the status to published
            selectedListing.setListingState(ListingState.PUBLISHED);
            // Save the updated listing into database
            listingRepository.save(selectedListing);
            // Display a success message
            return new ApiResponse(selectedListing, 200, "Your listing was published successfully", ResponseStatus.SUCCESS);
            // Throw the exception ListingNotFoundException
        } else throw new ListingNotFoundException("No listing found with provided uuid");
    }

    @Override
    public ApiResponse unPublishListing(UUID listingId) {
        // Retrieving the listing using the primary key
        Optional<Listing> listing = listingRepository.findById(listingId);
        Listing selectedListing = listing.orElse(null);
        //Check if the listing exist
        if (selectedListing == null) {
            logger.info("Listing with id : {} was not found", listingId);
            //Throw the exception ListingNotFoundException
            throw new ListingNotFoundException("No listing found with provided uuid");
        }
        selectedListing.setListingState(ListingState.DRAFT);
        // Save the updated listing into database
        listingRepository.save(selectedListing);
        // Display a success message
        return new ApiResponse(selectedListing, 200, "Your listing was unpublished successfully", ResponseStatus.SUCCESS);
    }

}
