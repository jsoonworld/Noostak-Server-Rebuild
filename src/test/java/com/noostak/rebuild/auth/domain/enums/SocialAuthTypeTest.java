package com.noostak.rebuild.auth.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("소셜 인증 타입 테스트")
class SocialAuthTypeTest {

    @Nested
    @DisplayName("성공 케이스")
    class SuccessCases {

        @ParameterizedTest
        @DisplayName("정상적인 소셜 인증 타입 문자열이 주어지면 해당 enum을 반환한다.")
        @ValueSource(strings = {"KAKAO", "GOOGLE", "APPLE"})
        void validSocialAuthType(String input) {
            SocialAuthType result = SocialAuthType.from(input);
            assertThat(result.getProvider()).isEqualTo(input);
        }

        @ParameterizedTest
        @DisplayName("소셜 인증 타입 문자열이 소문자거나 혼합되어 있어도 성공적으로 매핑된다.")
        @ValueSource(strings = {"kakao", "Google", "aPpLe"})
        void caseInsensitiveInput(String input) {
            SocialAuthType result = SocialAuthType.from(input);
            assertThat(result.getProvider()).isIn("KAKAO", "GOOGLE", "APPLE");
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases {

        @ParameterizedTest
        @DisplayName("존재하지 않는 소셜 인증 타입이면 예외가 발생한다.")
        @ValueSource(strings = {"FACEBOOK", "NAVER", "TWITTER", "", " ", "goolge", "앱플"})
        void invalidSocialAuthType(String input) {
            assertThatThrownBy(() -> SocialAuthType.from(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("존재 하지 않는 소셜 로그인 타입입니다.");
        }

        @Test
        @DisplayName("null이 주어지면 예외가 발생한다.")
        void nullInput() {
            assertThatThrownBy(() -> SocialAuthType.from(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("존재 하지 않는 소셜 로그인 타입입니다.");
        }
    }
}
