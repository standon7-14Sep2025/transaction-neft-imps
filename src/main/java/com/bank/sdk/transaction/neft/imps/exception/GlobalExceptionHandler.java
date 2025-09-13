package com.bank.sdk.transaction.neft.imps.exception;

import com.bank.sdk.transaction.neft.imps.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
/*
Combination of ControllerAdvice and ResponseBody
 */
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponseDto response = new ErrorResponseDto(400, ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    /*
MethodArgumentNotValidException is specific to Request Dto value validations
added in NEFTTransferRequestDto like @NotNull and others
 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Extract the first error message (you can enhance this if you want to return all)
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request");

        ErrorResponseDto response = new ErrorResponseDto(400, errorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TransactionTypeException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(TransactionTypeException ex) {
        ErrorResponseDto response = new ErrorResponseDto(400, ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAll(Exception ex) {
        ErrorResponseDto response = new ErrorResponseDto(500, "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

