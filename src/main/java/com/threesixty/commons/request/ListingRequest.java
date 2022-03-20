package com.threesixty.commons.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingRequest {

    @NotNull(message = "Vehicle is mandatory") @NotEmpty(message = "Vehicle is mandatory")
    private String vehicle;
    @NotNull(message = "Price is mandatory")
    private double price = 0;
}
