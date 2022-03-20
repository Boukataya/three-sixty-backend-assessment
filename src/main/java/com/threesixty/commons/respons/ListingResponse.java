package com.threesixty.commons.respons;

import com.threesixty.commons.enums.ListingState;
import com.threesixty.commons.request.ListingRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListingResponse extends ListingRequest {

    private UUID listingId;
    private String createdAt;
    private ListingState listingState;

}
