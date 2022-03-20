package com.threesixty.controllers;

import com.threesixty.services.listing.ListingServiceImpl;
import com.threesixty.commons.enums.ListingState;
import com.threesixty.commons.request.ListingRequest;
import com.threesixty.commons.respons.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ListingController {

    private static final Logger logger = LogManager.getLogger(ListingController.class);
    private final ListingServiceImpl listingService;

    // Injecting the service using thg the constructor
    public ListingController(ListingServiceImpl listingService) {
        this.listingService = listingService;
    }

    // Method to create a new listing using the url /api/dealerID/save-listing
    @PostMapping("/{dealerId}/save-listing")
    @ApiOperation(value = "Create a listing for a dealer")
    public ResponseEntity<ApiResponse> createListing(@Valid @RequestBody ListingRequest listingRequest, @PathVariable UUID dealerId) {
        logger.info("Processing the save of the new listing  : {}", listingRequest);
        ApiResponse response = listingService.createListing(listingRequest, dealerId);
        logger.info("Saving done with response  : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Method to find all dealer listings with specific status using the url /api/search/dealerID/state
    @GetMapping("/search/{dealerId}/{state}")
    @ApiOperation(value = "Find a dealer listings with specific state")
    public ResponseEntity<ApiResponse> findListing(@PathVariable UUID dealerId, @PathVariable ListingState state) {
        logger.info("Listings request of dealer id {} and State {}", dealerId, state);
        ApiResponse response = listingService.findListing(dealerId, state);
        System.out.println("response = " + response);
        logger.info("Search complete with the results : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Method to update a listing information using the url /api/update-listing/listingId
    @PutMapping("/update-listing/{listingId}")
    @ApiOperation(value = "update a listing")
    public ResponseEntity<ApiResponse> updateListing(@PathVariable UUID listingId, @Valid @RequestBody ListingRequest listingRequest) {
        logger.info("Start updating a listing with UUID: {}", listingId);
        ApiResponse response = listingService.updateListing(listingId, listingRequest);
        logger.info("Update Completed with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Method to publish a listing using the url /api/publish/listingId
    @PutMapping("/publish/{listingId}")
    @ApiOperation(value = "Publish a listing")
    public ResponseEntity<ApiResponse> publishListing(@PathVariable UUID listingId) {
        logger.info("Start a publishing request of listing UUID {}", listingId);
        ApiResponse response = listingService.publishListing(listingId);
        logger.info("Complete the publishing request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Method to unpublish a listing using the url /api/unpublish/listingId
    @PutMapping("/unpublish/{listingId}")
    @ApiOperation(value = "unpublish a listing")
    public ResponseEntity<ApiResponse> unpublishListing(@PathVariable UUID listingId) {
        logger.info("Start a unpublishing request of listing UUID {}", listingId);
        ApiResponse response = listingService.unPublishListing(listingId);
        logger.info("Complete the unpublishing request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
