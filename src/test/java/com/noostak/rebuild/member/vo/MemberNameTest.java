package com.noostak.rebuild.member.vo;

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
    class FailureCases{

        @ParameterizedTest
        @DisplayName("이름이 공백 문자로만 이루어진 경우 예외가 발생한다.")
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void nameIsBlank(String invalidName) {
            assertThatThrownBy(() -> MemberName.from(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이름은 공백으로만 구성될 수 없습니다.");
        }

        @Test
        @DisplayName("이름이 null인 경우 예외가 발생한다.")
        void nameIsNull() {
            assertThatThrownBy(() -> MemberName.from(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이름은 null 일 수 없습니다.");
        }

        @ParameterizedTest
        @DisplayName("이름이 10자를 초과하는 경우 예외가 발생한다.")
        @ValueSource(strings = {"01234567891", "01234567890123456789", "한글과영문혼합길이초과abcde"})
        void nameLengthExceeded(String invalidName) {
            assertThatThrownBy(() -> MemberName.from(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이름은 10자를 초과할 수 없습니다.");
        }

        @ParameterizedTest
        @DisplayName("이름에 특수문자가 포함된 경우 예외가 발생한다.")
        @CsvSource({
                "jsoon@worl",
                "jsoon#word",
                "jsoon$wold",
                "jsoon%wrld",
                "jsoon^orld",
                "jsoon&orld",
                "jsoo*world",
                "json(world",
                "json)world",
                "joon-world"
        })
        void nameContainsSpecialCharacters(String invalidName) {
            assertThatThrownBy(() -> MemberName.from(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("특수문자는 이름 구성에 포함시킬 수 없습니다.");
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
    }
}