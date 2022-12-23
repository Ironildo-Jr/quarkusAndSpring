package com.study.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String message;
    private List<ErrorMessage> erros;

    public static ErrorResponseDto createFromValidation(ConstraintViolationException constraintViolationException) {
        List<ErrorMessage> violacoes =
                constraintViolationException
                .getConstraintViolations()
                .stream()
                .map(cv -> new ErrorMessage(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());
        return new ErrorResponseDto("Erros de Validações", violacoes);

    }
}
