package com.noostak.rebuild.member.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MemberProfileImageKey {

    private static final String DEFAULT_KEY = "default/profile.png";
    public static final MemberProfileImageKey DEFAULT = new MemberProfileImageKey(DEFAULT_KEY, false);

    private final String value;

    protected MemberProfileImageKey() {
        this.value = null;
    }

    private MemberProfileImageKey(String value) {
        this(value, true);
    }

    private MemberProfileImageKey(String value, boolean validate) {
        if (validate) {
            validate(value);
        }
        this.value = value;
    }

    public static MemberProfileImageKey from(String value) {
        return new MemberProfileImageKey(value);
    }

    public static MemberProfileImageKey fromOrDefault(String value) {
        if (value == null || value.isBlank()) {
            return DEFAULT;
        }
        return new MemberProfileImageKey(value);
    }

    public String value() {
        return value;
    }

    public boolean isDefault() {
        return DEFAULT_KEY.equals(this.value);
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("프로필 이미지 키는 null 또는 빈 문자열일 수 없습니다.");
        }
    }
}
