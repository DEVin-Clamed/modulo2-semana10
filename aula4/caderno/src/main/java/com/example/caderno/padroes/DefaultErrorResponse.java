package com.example.caderno.padroes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultErrorResponse {
    private int status;
    private String message;
    private Throwable cause;
}
