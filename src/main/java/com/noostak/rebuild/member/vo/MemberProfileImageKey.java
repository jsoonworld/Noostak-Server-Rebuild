package com.noostak.rebuild.member.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MemberProfileImageKey {

    private static final String DEFAULT_KEY = "default/profile.png";
    public static final MemberProfileImageKey DEFAULT = new MemberProfileImageKey(DEFAULT_KEY, false); // 검증 생략

    private final String value;

    protected MemberProfileImageKey() {
        this.value = null;
    }

    private MemberProfileImageKey(String value) {
        validate(value);
        this.value = value;
    }

    private MemberProfileImageKey(String value, boolean skipValidation) {
        this.value = value;
    }

    public static MemberProfileImageKey from(String value) {
        return new MemberProfileImageKey(value);
    }

    public static MemberProfileImageKey fromOrDefault(String value) {
        if (value == null || value.isBlank()) {
            return DEFAULT;
        }
        return from(value);
    }

    public String value() {
        return value;
    }

    public boolean isDefault() {
        return DEFAULT_KEY.equals(this.value);
    }

    private static void validate(String value) {
        validateNotNull(value);
        validateNotBlank(value);
    }

    private static void validateNotNull(String value) {
        if (value == null) {
            throw new IllegalArgumentException("프로필 이미지 키는 null 일 수 없습니다.");
        }
    }

    private static void validateNotBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("프로필 이미지 키는 빈 문자열 일 수 없습니다.");
        }
    }

}
