package com.phenikaa.vietsecond.Utils.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private int statusCode;
    private String message;

    public ErrorMessage toErrorMessage(){
        ErrorMessage errorMessage = new ErrorMessage(statusCode,message);
        return errorMessage;
    }
}
