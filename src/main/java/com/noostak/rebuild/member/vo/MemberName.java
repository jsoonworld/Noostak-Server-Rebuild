package com.noostak.rebuild.member.vo;

public class MemberName {

    private String value;

    private MemberName(String value) {
        validate(value);
        this.value = value;
    }

    public static MemberName of(String value) {
        return new MemberName(value);
    }

    private void validate(String value) {
        validateBlank(value);
    }

    private void validateBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }
}
