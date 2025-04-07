package com.noostak.rebuild.member.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Nested
    @DisplayName("성공 케이스")
    class SuccessCases {

        @Test
        @DisplayName("정상적인 키가 주어졌을 때 객체가 생성된다.")
        void validKey() {
            String key = "profile/images/user123.png";

            MemberProfileImageKey imageKey = MemberProfileImageKey.from(key);

            assertThat(imageKey.value()).isEqualTo(key);
        }

        @Test
        @DisplayName("fromOrDefault에 null이 입력되면 기본 키가 반환된다.")
        void fromOrDefaultWithNull() {
            MemberProfileImageKey imageKey = MemberProfileImageKey.fromOrDefault(null);

            assertThat(imageKey).isEqualTo(MemberProfileImageKey.DEFAULT);
        }

        @Test
        @DisplayName("fromOrDefault에 빈 문자열이 입력되면 기본 키가 반환된다.")
        void fromOrDefaultWithBlank() {
            MemberProfileImageKey imageKey = MemberProfileImageKey.fromOrDefault("   ");

            assertThat(imageKey).isEqualTo(MemberProfileImageKey.DEFAULT);
        }

        @Test
        @DisplayName("기본 키는 isDefault()가 true를 반환한다.")
        void isDefaultTrue() {
            MemberProfileImageKey imageKey = MemberProfileImageKey.fromOrDefault(null);

            assertThat(imageKey.isDefault()).isTrue();
        }

        @Test
        @DisplayName("사용자 키는 isDefault()가 false를 반환한다.")
        void isDefaultFalse() {
            MemberProfileImageKey imageKey = MemberProfileImageKey.from("profile/images/custom.png");

            assertThat(imageKey.isDefault()).isFalse();
        }
    }
}