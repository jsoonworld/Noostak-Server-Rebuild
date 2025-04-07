package com.noostak.rebuild.global.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GlobalExceptionHandler 테스트")
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    static class DummyBaseException extends BaseException {
        DummyBaseException() {
            super(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Nested
    @DisplayName("BaseException 처리")
    class HandleBaseException {

        private final DummyBaseException exception = new DummyBaseException();
        private final ResponseEntity<ErrorResponse> response = handler.handleBaseException(exception);

        @Test
        @DisplayName("HTTP 상태 코드가 INTERNAL_SERVER_ERROR 이어야 한다")
        void statusCodeShouldBeInternalServerError() {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @Test
        @DisplayName("응답 바디가 null이 아니어야 한다")
        void responseBodyShouldNotBeNull() {
            assertThat(response.getBody()).isNotNull();
        }

        @Test
        @DisplayName("ErrorResponse.status 필드는 500 이어야 한다")
        void errorResponseStatusShouldBe500() {
            assertThat(response.getBody().getStatus()).isEqualTo(500);
        }

        @Test
        @DisplayName("ErrorResponse.message 필드는 정의된 에러 메시지를 포함해야 한다")
        void errorResponseMessageShouldContainDefinedMessage() {
            assertThat(response.getBody().getMessage()).contains(GlobalErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        }
    }

    @Nested
    @DisplayName("기타 예외 처리")
    class HandleUnexpectedException {

        private final Exception exception = new IllegalStateException("테스트용 예외");
        private final ResponseEntity<ErrorResponse> response = handler.handleInternalServerError(exception);

        @Test
        @DisplayName("HTTP 상태 코드가 INTERNAL_SERVER_ERROR 이어야 한다")
        void statusCodeShouldBeInternalServerError() {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @Test
        @DisplayName("응답 바디가 null이 아니어야 한다")
        void responseBodyShouldNotBeNull() {
            assertThat(response.getBody()).isNotNull();
        }

        @Test
        @DisplayName("ErrorResponse.status 필드는 500 이어야 한다")
        void errorResponseStatusShouldBe500() {
            assertThat(response.getBody().getStatus()).isEqualTo(500);
        }

        @Test
        @DisplayName("ErrorResponse.message 필드는 글로벌 에러 메시지를 포함해야 한다")
        void errorResponseMessageShouldContainGlobalErrorMessage() {
            assertThat(response.getBody().getMessage()).contains(GlobalErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}
