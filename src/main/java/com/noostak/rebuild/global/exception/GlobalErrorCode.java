package com.noostak.rebuild.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    public static final String PREFIX = "[GLOBAL ERROR] ";

    private final HttpStatus status;
    private final String rawMessage;

    @Override
    public String getMessage() {
        return PREFIX + rawMessage;
    }

    @Override
    public String getMessage(Object... args) {
        return PREFIX + String.format(rawMessage, args);
    }

    @Override
    public String getRawMessage() {
        return rawMessage;
    }
}

