package com.noostak.rebuild.member.domain.vo;

import com.noostak.rebuild.member.exception.MemberErrorCode;
import com.noostak.rebuild.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("멤버 이름 테스트")
class MemberNameTest {

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases {

        @ParameterizedTest
        @DisplayName("이름이 공백 문자로만 이루어진 경우 예외가 발생한다.")
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void nameIsBlank(String invalidName) {
            assertThatThrownBy(() -> MemberName.from(invalidName))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.BLANK_MEMBER_NAME.getRawMessage());
        }

        @Test
        @DisplayName("이름이 null인 경우 예외가 발생한다.")
        void nameIsNull() {
            assertThatThrownBy(() -> MemberName.from(null))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.NULL_MEMBER_NAME.getRawMessage());
        }

        @ParameterizedTest
        @DisplayName("이름이 10자를 초과하는 경우 예외가 발생한다.")
        @ValueSource(strings = {
                "01234567891",
                "01234567890123456789",
                "한글과영문혼합길이초과abcde"
        })
        void nameLengthExceeded(String invalidName) {
            assertThatThrownBy(() -> MemberName.from(invalidName))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.TOO_LONG_MEMBER_NAME.getRawMessage());
        }

        @ParameterizedTest
        @DisplayName("이름에 특수문자가 포함된 경우 예외가 발생한다.")
        @CsvSource({
                "jsoon@worl, @",
                "jsoon#word, #",
                "jsoon$wold, $",
                "jsoon%wrld, %",
                "jsoon^orld, ^",
                "jsoon&orld, &",
                "jsoo*world, *",
                "json(world, (",
                "json)world, )",
                "joon-world, -"
        })
        void nameContainsSpecialCharacters(String invalidName, String specialChar) {
            assertThatThrownBy(() -> MemberName.from(invalidName))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.SPECIAL_CHAR_IN_MEMBER_NAME.getRawMessage());
        }

        @ParameterizedTest
        @DisplayName("이름에 허용되지 않은 문자(한글, 영문 외 언어)가 포함된 경우 예외가 발생한다.")
        @CsvSource({
                "张伟, 张",
                "山田太郎, 山",
                "محمد, م",
                "Алексей, А",
                "Δημήτρης, Δ",
                "𓀀, 𓀀"
        })
        void nameContainsNonKoreanEnglishCharacters(String invalidName, String invalidChar) {
            assertThatThrownBy(() -> MemberName.from(invalidName))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.INVALID_CHAR_IN_MEMBER_NAME.getRawMessage());
        }
    }

    @Nested
    @DisplayName("성공 케이스")
    class SuccessCases {

        @Test
        @DisplayName("이름이 공백을 포함하여 10자 이하인 경우 성공적으로 생성된다.")
        void createMemberNameSuccessfully() {
            String validName = "j        0";
            MemberName memberName = MemberName.from(validName);

            assertThat(memberName.value()).isEqualTo(validName);
        }

        @Test
        @DisplayName("이름이 10자 이하인 경우 성공적으로 생성된다.")
        void createMemberNameWithValidLength() {
            String validName = "jsoonworld";
            MemberName memberName = MemberName.from(validName);

            assertThat(memberName.value()).isEqualTo(validName);
        }

        @ParameterizedTest
        @DisplayName("이름에 다양한 이모지가 포함된 경우 성공적으로 생성된다.")
        @ValueSource(strings = {
                "😊",
                "🚀",
                "❤️",
                "🔥수",
                "🌟리",
                "😎글",
                "💡수",
                "🐱범",
                "🎉동",
                "🍕수"
        })
        void createMemberNameWithEmoji(String validName) {
            MemberName memberName = MemberName.from(validName);
            assertThat(memberName.value()).isEqualTo(validName);
        }

        @ParameterizedTest
        @DisplayName("이름에 한글, 영문, 숫자가 포함된 경우 성공적으로 생성된다.")
        @ValueSource(strings = {
                "권장순", "James", "홍James", "김0순", "kim99", "김Soon9", "so on 9", "이름123", "Name 0"
        })
        void createMemberNameWithKoreanEnglishNumber(String validName) {
            MemberName memberName = MemberName.from(validName);
            assertThat(memberName.value()).isEqualTo(validName);
        }
    }
}
