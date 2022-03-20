package com.threesixty.commons.respons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.threesixty.commons.enums.ResponseStatus;
import lombok.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse {

    private Object data;
    private Integer code;
    private String message;
    private ResponseStatus status;

}