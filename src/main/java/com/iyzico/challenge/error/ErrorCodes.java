package com.iyzico.challenge.error;

import com.iyzico.challenge.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ErrorCodes implements ErrorCode {

    SYSTEM_FAILURE(-1, "ErrorCodes.SYSTEM_FAILURE", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND(100, "ErrorCodes.DATA_NOT_FOUND", HttpStatus.NOT_FOUND),
    FLIGHT_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.FLIGHT_DATA_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    SEAT_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.SEAT_DATA_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    FLIGHT_NAME_CAN_NOT_BE_EMPTY(101,"ErrorCodes.FLIGHT_NAME_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    FLIGHT_SEAT_CAN_NOT_BE_EMPTY(101,"ErrorCodes.FLIGHT_SEAT_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    FLIGHT_DESCRIPTION_CAN_NOT_BE_EMPTY(101,"ErrorCodes.FLIGHT_DESCRIPTION_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    SEAT_NUMBER_CAN_NOT_BE_EMPTY(101,"ErrorCodes.SEAT_NUMBER_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    ;

    private ErrorCodes(Integer code, String langKey, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.langKey = langKey;
    }


    private Integer code;

    private String langKey;

    private HttpStatus httpStatus;

    public Integer getCode() {
        return code;
    }

    public String getLangKey() {
        return langKey;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @param code
     * @return
     */
    public ErrorCodes findByCode(Integer code) {
        return Arrays.asList(ErrorCodes.values()).stream().filter(f -> f.getCode().equals(code)).findFirst().orElse(ErrorCodes.SYSTEM_FAILURE);
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String langKey() {
        return this.langKey;
    }
}
