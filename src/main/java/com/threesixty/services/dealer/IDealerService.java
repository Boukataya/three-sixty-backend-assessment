package com.threesixty.services.dealer;

import com.threesixty.commons.request.DealerRequest;
import com.threesixty.commons.respons.ApiResponse;

public interface IDealerService {

    ApiResponse saveDealer(DealerRequest dealerRequest);

}
