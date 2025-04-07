package com.noostak.rebuild.member.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("멤버 프로필 이미지 키 테스트")
class MemberProfileImageKeyTest {

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases {

        @Test
        @DisplayName("프로필 이미지 키가 null인 경우 예외가 발생한다.")
        void nullKey() {
            // given
            String key = null;

            // when & then
            assertThatThrownBy(() -> MemberProfileImageKey.from(key))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("프로필 이미지 키는 null 일 수 없습니다.");
        }

        @Test
        @DisplayName("프로필 이미지 키가 빈 문자열인 경우 예외가 발생한다.")
        void emptyKey() {
            // given
            String key = "";

            // when & then
            assertThatThrownBy(() -> MemberProfileImageKey.from(key))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("프로필 이미지 키는 빈 문자열 일 수 없습니다.");
        }
    }
}