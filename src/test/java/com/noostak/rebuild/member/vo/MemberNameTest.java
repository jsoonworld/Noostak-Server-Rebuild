package com.noostak.rebuild.member.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    }
}