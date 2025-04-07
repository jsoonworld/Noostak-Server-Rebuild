package com.noostak.rebuild.member.enums;

import java.util.Arrays;

public enum MemberAccountStatus {
    ACTIVE("활성"),
    INACTIVE("비활성");

    private static final String INVALID_STATUS_MESSAGE = "존재하지 않는 계정 상태입니다";

    private final String value;

    MemberAccountStatus(String value) {
        this.value = value;
    }

    public static MemberAccountStatus from(String value) {
        validate(value);
        return Arrays.stream(values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_STATUS_MESSAGE + ": " + value));
    }

    public String value() {
        return value;
    }
    
    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isInactive() {
        return this == INACTIVE;
    }

    private static void validate(String value) {
        if (value == null) {
            throw new IllegalArgumentException("계정 상태는 null 일 수 없습니다.");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException("계정 상태는 공백일 수 없습니다.");
        }
    }
}
