package com.iyzico.challenge.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public class IyzicoException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SIGN_ERROR_DETAIL = "An unexpected error occurred! Please contact the api support!";


    private final String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String errorDetail;

    private final ErrorCode errorCode;

    public IyzicoException(ErrorCode errorCode, String errorMessage, String errorDetail) {
        super(errorCode.getName());
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String[] getArgs() {
        return args;
    }

    private String[] args;

    public IyzicoException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getName(), cause);
        this.errorCode = errorCode;
        this.errorMessage = cause.getMessage();
        this.errorDetail = !StringUtils.isEmpty(cause.getMessage()) ? cause.getMessage() : SIGN_ERROR_DETAIL;
    }

    public IyzicoException(ErrorCode errorCode) {
        super(errorCode.getName());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getName();
        this.errorDetail = null;
    }

    public IyzicoException(ErrorCode errorCode, String[] args) {
        super(errorCode.getName());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getName();
        this.args = args;
        this.errorDetail = null;
    }


    public IyzicoException(ErrorCode errorCode, String errorDetail) {
        super(errorCode.getName());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getName();
        this.errorDetail = errorDetail;
    }
}
