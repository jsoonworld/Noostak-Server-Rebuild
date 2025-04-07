package com.noostak.rebuild.member.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MemberName {

    private final String value;

    protected MemberName() {
        this.value = null;
    }

    private MemberName(String value) {
        validate(value);
        this.value = value;
    }

    public static MemberName from(String value) {
        return new MemberName(value);
    }

    public String value() {
        return value;
    }

    private void validate(String value) {
        validateNotNullOrBlank(value);
    }

    private void validateNotNullOrBlank(String value) {
        if (value == null) {
            throw new IllegalArgumentException("이름은 null 일 수 없습니다.");
        }

        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백으로만 구성될 수 없습니다.");
        }
    }
}
