package com.noostak.rebuild.member.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MemberName {

    private static final int MAX_LENGTH = 10;
    private static final String SPECIAL_CHAR_PATTERN = ".*[~`!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?].*";

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
        if (value.matches(SPECIAL_CHAR_PATTERN)) {
            throw new IllegalArgumentException("특수문자는 이름 구성에 사용될 수 없습니다.");
        }

        for (int cp : value.codePoints().toArray()) {
            if (isInvalidCharacter(cp)) {
                String invalidChar = new String(Character.toChars(cp));
                throw new IllegalArgumentException("이름에 허용되지 않은 문자(" + invalidChar + ")가 포함되어 있습니다.");
            }
        }
    }

    private boolean isInvalidCharacter(int cp) {
        return !(isKorean(cp) || isEnglish(cp) || Character.isDigit(cp) || Character.isSpaceChar(cp) || isEmoji(cp));
    }

    private boolean isKorean(int cp) {
        return cp >= 0xAC00 && cp <= 0xD7A3;
    }

    private boolean isEnglish(int cp) {
        return (cp >= 'a' && cp <= 'z') || (cp >= 'A' && cp <= 'Z');
    }

    private boolean isEmoji(int cp) {
        return (cp >= 0x1F600 && cp <= 0x1F64F)
                || (cp >= 0x1F300 && cp <= 0x1F5FF)
                || (cp >= 0x1F680 && cp <= 0x1F6FF)
                || (cp >= 0x2600 && cp <= 0x26FF)
                || (cp >= 0x2700 && cp <= 0x27BF)
                || (cp >= 0xFE00 && cp <= 0xFE0F)
                || (cp >= 0x1F900 && cp <= 0x1F9FF)
                || (cp >= 0x1FA70 && cp <= 0x1FAFF)
                || (cp >= 0x1F1E6 && cp <= 0x1F1FF);
    }
}
