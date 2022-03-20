package com.threesixty.commons.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerRequest {
    @NotNull(message = "Dealer name is mandatory")
    @NotEmpty(message = "Dealer name is mandatory")
    private String name;
}
