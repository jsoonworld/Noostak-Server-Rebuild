package com.noostak.rebuild.member.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MemberName {

    private static final int MAX_LENGTH = 10;
    private static final String SPECIAL_LETTERS = ".*[~`!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?].*";

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
        validateLength(value);
        validateCharacter(value);
    }

    private void validateNotNullOrBlank(String value) {
        if (value == null) {
            throw new IllegalArgumentException("이름은 null 일 수 없습니다.");
        }

        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백으로만 구성될 수 없습니다.");
        }
    }

    private void validateLength(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 10자를 초과할 수 없습니다.");
        }
    }

    private void validateCharacter(String value) {
        if (value.matches(SPECIAL_LETTERS)) {
            throw new IllegalArgumentException("특수문자는 이름 구성에 포함시킬 수 없습니다.");
        }
    }
}
