package com.noostak.rebuild.member.domain.exception;

import com.noostak.rebuild.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    INVALID_ACCOUNT_STATUS(HttpStatus.BAD_REQUEST, "존재하지 않는 계정 상태입니다."),
    NULL_ACCOUNT_STATUS(HttpStatus.BAD_REQUEST, "계정 상태는 null일 수 없습니다."),
    BLANK_ACCOUNT_STATUS(HttpStatus.BAD_REQUEST, "계정 상태는 공백일 수 없습니다."),

    NULL_MEMBER_NAME(HttpStatus.BAD_REQUEST, "이름은 null일 수 없습니다."),
    BLANK_MEMBER_NAME(HttpStatus.BAD_REQUEST, "이름은 공백으로만 구성될 수 없습니다."),
    TOO_LONG_MEMBER_NAME(HttpStatus.BAD_REQUEST, "이름은 10자를 초과할 수 없습니다."),
    SPECIAL_CHAR_IN_MEMBER_NAME(HttpStatus.BAD_REQUEST, "특수문자는 이름 구성에 사용될 수 없습니다."),
    INVALID_CHAR_IN_MEMBER_NAME(HttpStatus.BAD_REQUEST, "이름에 허용되지 않은 문자가 포함되어 있습니다."),

    NULL_PROFILE_IMAGE_KEY(HttpStatus.BAD_REQUEST, "프로필 이미지 키는 null일 수 없습니다."),
    BLANK_PROFILE_IMAGE_KEY(HttpStatus.BAD_REQUEST, "프로필 이미지 키는 빈 문자열일 수 없습니다."),

    ;

    public static final String PREFIX = "[MEMBER ERROR] ";

    private final HttpStatus status;
    private final String rawMessage;

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getRawMessage() {
        return PREFIX + rawMessage;
    }
}
