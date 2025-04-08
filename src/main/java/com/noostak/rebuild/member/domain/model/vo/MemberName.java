package com.noostak.rebuild.member.domain.model.vo;

import com.noostak.rebuild.member.domain.exception.MemberErrorCode;
import com.noostak.rebuild.member.domain.exception.MemberException;
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
            throw new MemberException(MemberErrorCode.NULL_MEMBER_NAME);
        }

        if (value.isBlank()) {
            throw new MemberException(MemberErrorCode.BLANK_MEMBER_NAME);
        }
    }

    private void validateLength(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new MemberException(MemberErrorCode.TOO_LONG_MEMBER_NAME);
        }
    }

    private void validateCharacter(String value) {
        if (value.matches(SPECIAL_CHAR_PATTERN)) {
            throw new MemberException(MemberErrorCode.SPECIAL_CHAR_IN_MEMBER_NAME);
        }

        for (int cp : value.codePoints().toArray()) {
            if (isInvalidCharacter(cp)) {
                throw new MemberException(MemberErrorCode.INVALID_CHAR_IN_MEMBER_NAME);
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
