package com.lulugyda;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MovieException extends RuntimeException {
    String id;
    String message;

}

