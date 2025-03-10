package com.swd.pregnancycare.exception;

import com.swd.pregnancycare.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CentralException {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> centralLog(Exception e){
        BaseResponse response = new BaseResponse();

        response.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        return ResponseEntity.internalServerError().body(response);
    }
    @ExceptionHandler({AppException.class})
    public ResponseEntity<?> centralLog(AppException e){
        BaseResponse response = new BaseResponse();
        ErrorCode errorCode= e.getErrorCode();
        response.setMessage(errorCode.getMessage());
        response.setCode(errorCode.getCode());
        return ResponseEntity.internalServerError().body(response);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e){
        BaseResponse response = new BaseResponse();
        String enumkey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumkey);
        response.setMessage(errorCode.getMessage());
        response.setCode(errorCode.getCode());
        return ResponseEntity.internalServerError().body(response);
    }


}
