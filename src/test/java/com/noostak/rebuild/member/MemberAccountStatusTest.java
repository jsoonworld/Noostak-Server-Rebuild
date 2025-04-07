package com.noostak.rebuild.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("멤버 계정 상태 테스트")
class MemberAccountStatusTest {

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases {

        @ParameterizedTest
        @DisplayName("존재하지 않는 상태값으로 조회 시 예외가 발생한다")
        @ValueSource(strings = {"DEACTIVATED", "BLOCKED", "SLEEP", "탈퇴", " "})
        void from_InvalidStatus_ThrowsException(String invalidStatus) {
            assertThatThrownBy(() -> MemberAccountStatus.from(invalidStatus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("존재하지 않는 계정 상태입니다");
        }

    }

    @Nested
    @DisplayName("성공 케이스")
    class SuccessCases {

        @ParameterizedTest
        @DisplayName("유효한 상태값 입력 시 정상적으로 상태를 반환한다")
        @ValueSource(strings = {"ACTIVE", "INACTIVE", "active", "inactive"})
        void from_ValidStatus_ReturnsEnum(String input) {
            MemberAccountStatus status = MemberAccountStatus.from(input);
            assertThat(status.name()).isEqualTo(input.toUpperCase());
        }
    }
}
