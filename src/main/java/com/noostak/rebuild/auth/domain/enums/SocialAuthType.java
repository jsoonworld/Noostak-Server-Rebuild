package com.noostak.rebuild.auth.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SocialAuthType {

    KAKAO("KAKAO"),
    GOOGLE("GOOGLE"),
    APPLE("APPLE"),
    ;

    private final String provider;

    public static SocialAuthType from(String givenProvider) {
        return Arrays.stream(values())
                .filter(type -> type.provider.equalsIgnoreCase(givenProvider))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 소셜 로그인 타입입니다."));
    }
}
