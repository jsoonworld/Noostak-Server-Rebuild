package com.noostak.rebuild.member.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("멤버 이름 테스트")
class MemberNameTest {

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases{

        @ParameterizedTest
        @DisplayName("이름이 공백으로만 이루어진 경우 실패한다.")
        @CsvSource({
                "",
                " ",
        })
        void nameIsBlank(String invalidName) {
            assertThrows(IllegalArgumentException.class, () -> MemberName.of(invalidName));
        }
    }
}