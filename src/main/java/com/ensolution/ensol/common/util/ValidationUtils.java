package com.ensolution.ensol.common.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidationUtils {

  public static <T> ResponseEntity<ApiResponseMessage<T>> handleBindingErrors(BindingResult result) {
    String errorMsg = result.getFieldErrors().stream()
        .map(err -> err.getField() + ": " + err.getDefaultMessage())
        .collect(Collectors.joining(", "));

    ApiResponseMessage<T> error = new ApiResponseMessage<>(false, errorMsg, null);
    return ResponseEntity.badRequest().body(error);
  }
}
