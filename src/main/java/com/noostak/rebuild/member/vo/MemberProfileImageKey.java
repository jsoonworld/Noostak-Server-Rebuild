package com.noostak.rebuild.member.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MemberProfileImageKey {

    private final String value;

    protected MemberProfileImageKey() {
        this.value = null;
    }

    private MemberProfileImageKey(String value) {
        validate(value);
        this.value = value;
    }

    public static MemberProfileImageKey from(String value) {
        return new MemberProfileImageKey(value);
    }

    public String value() {
        return value;
    }

    private void validate(String value) {
        validateNotNull(value);
    }

    private void validateNotNull(String value) {
        if (value == null) {
            throw new IllegalArgumentException("프로필 이미지 키는 null 일 수 없습니다.");
        }
    }
}
