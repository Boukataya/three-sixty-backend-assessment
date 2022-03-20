package com.threesixty.controllers;

import com.threesixty.commons.request.DealerRequest;
import com.threesixty.services.dealer.DealerServiceImpl;
import com.threesixty.commons.respons.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DealerController {

    private static final Logger logger = LogManager.getLogger(ListingController.class);
    private final DealerServiceImpl dealerService;

    public DealerController(DealerServiceImpl dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping("/register-dealer")
    public ResponseEntity<ApiResponse> saveDealer(@RequestBody DealerRequest dealerRequest) {
        logger.info("Processing the register of new dealer  : {}", dealerRequest);
        ApiResponse response = dealerService.saveDealer(dealerRequest);
        logger.info("Complete new dealer registration request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
