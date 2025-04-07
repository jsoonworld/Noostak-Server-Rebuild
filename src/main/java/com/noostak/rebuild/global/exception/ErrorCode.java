package com.noostak.rebuild.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getStatus();
    String getRawMessage();

    default String getMessage() {
        return getRawMessage();
    }

    default String getMessage(Object... args) {
        return String.format(getRawMessage(), args);
    }
}
