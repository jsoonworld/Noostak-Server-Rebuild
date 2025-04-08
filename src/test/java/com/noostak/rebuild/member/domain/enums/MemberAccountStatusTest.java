package com.noostak.rebuild.member.domain.enums;

import com.noostak.rebuild.member.domain.exception.MemberErrorCode;
import com.noostak.rebuild.member.domain.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("멤버 계정 상태 테스트")
class MemberAccountStatusTest {

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases {

        @ParameterizedTest
        @DisplayName("존재하지 않는 상태값으로 조회 시 예외가 발생한다")
        @ValueSource(strings = {"DEACTIVATED", "BLOCKED", "SLEEP", "탈퇴"})
        void from_InvalidStatus_ThrowsException(String invalidStatus) {
            assertThatThrownBy(() -> MemberAccountStatus.from(invalidStatus))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.INVALID_ACCOUNT_STATUS.getRawMessage());
        }

        @ParameterizedTest
        @DisplayName("공백 상태값 입력 시 예외가 발생한다")
        @ValueSource(strings = {" ", "  ", "\n", "\t"})
        void from_BlankStatus_ThrowsException(String blankStatus) {
            assertThatThrownBy(() -> MemberAccountStatus.from(blankStatus))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.BLANK_ACCOUNT_STATUS.getRawMessage());
        }

        @ParameterizedTest
        @DisplayName("null 입력 시 예외가 발생한다")
        @NullSource
        void from_Null_ThrowsException(String input) {
            assertThatThrownBy(() -> MemberAccountStatus.from(input))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining(MemberErrorCode.NULL_ACCOUNT_STATUS.getRawMessage());
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

        @ParameterizedTest
        @DisplayName("isActive(), isInactive() 메서드가 정확하게 동작한다")
        @ValueSource(strings = {"ACTIVE", "INACTIVE"})
        void statusCheckMethodsWorkCorrectly(String input) {
            MemberAccountStatus status = MemberAccountStatus.from(input);

            boolean isActiveInput = input.equalsIgnoreCase("ACTIVE");
            assertThat(status.isActive()).isEqualTo(isActiveInput);
            assertThat(status.isInactive()).isEqualTo(!isActiveInput);
        }
    }
}
