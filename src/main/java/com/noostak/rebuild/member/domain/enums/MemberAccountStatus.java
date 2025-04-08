package com.noostak.rebuild.member.domain.enums;

import com.noostak.rebuild.member.domain.exception.MemberErrorCode;
import com.noostak.rebuild.member.domain.exception.MemberException;

import java.util.Arrays;

public enum MemberAccountStatus {
    ACTIVE("활성"),
    INACTIVE("비활성");

    private final String value;

    MemberAccountStatus(String value) {
        this.value = value;
    }

    public static MemberAccountStatus from(String value) {
        validate(value);
        return Arrays.stream(values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_ACCOUNT_STATUS));
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
            throw new MemberException(MemberErrorCode.NULL_ACCOUNT_STATUS);
        }
        if (value.isBlank()) {
            throw new MemberException(MemberErrorCode.BLANK_ACCOUNT_STATUS);
        }
    }
}
