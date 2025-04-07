package com.noostak.rebuild.member.enums;

import java.util.Arrays;

public enum MemberAccountStatus {
    ACTIVE("활성"),
    INACTIVE("비활성");

    private final String message;

    MemberAccountStatus(String message) {
        this.message = message;
    }

    public static MemberAccountStatus from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("존재하지 않는 계정 상태입니다: " + value);
        }

        return Arrays.stream(values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정 상태입니다: " + value));
    }

    public String message() {
        return message;
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isInactive() {
        return this == INACTIVE;
    }
}
