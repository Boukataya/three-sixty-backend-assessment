package com.threesixty.services.dealer;

import com.threesixty.models.Dealer;
import com.threesixty.repsitories.DealerRepository;
import com.threesixty.commons.enums.ResponseStatus;
import com.threesixty.commons.request.DealerRequest;
import com.threesixty.commons.respons.ApiResponse;
import com.threesixty.services.listing.ListingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DealerServiceImpl implements IDealerService {

    private static final Logger logger = LogManager.getLogger(ListingServiceImpl.class);
    private final DealerRepository dealerRepository;

    public DealerServiceImpl(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    @Override
    public ApiResponse saveDealer(DealerRequest dealerRequest) {

        if (dealerRequest.getName().trim().isEmpty()) {
            logger.info("Invalid dealer name");
            throw new IllegalArgumentException("Dealer name is mandatory");
        }
        Dealer dealer = new Dealer(dealerRequest.getName());

        dealerRepository.save(dealer);
        return new ApiResponse(dealer, 200, "New dealer was registered successfully", ResponseStatus.SUCCESS);

    }

}
