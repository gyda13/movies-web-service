package com.lulugyda.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MovieException extends RuntimeException {
    String id;
    String message;

}

