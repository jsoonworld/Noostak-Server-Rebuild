package com.noostak.rebuild.member.domain.model;

import com.noostak.rebuild.member.domain.enums.MemberAccountStatus;
import com.noostak.rebuild.member.domain.model.vo.MemberName;
import com.noostak.rebuild.member.domain.model.vo.MemberProfileImageKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Member 도메인 테스트")
class MemberTest {

    private final MemberName name = MemberName.from("장순");
    private final MemberProfileImageKey profileImageKey = MemberProfileImageKey.from("profile/1.png");

    @Nested
    @DisplayName("생성 테스트")
    class Create {

        @Test
        @DisplayName("정상적으로 생성된다.")
        void createSuccessfully() {
            Member member = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);

            assertThat(member.name()).isEqualTo(name);
            assertThat(member.profileImageKey()).isEqualTo(profileImageKey);
            assertThat(member.accountStatus()).isEqualTo(MemberAccountStatus.ACTIVE);
        }
    }

    @Nested
    @DisplayName("상태 확인 테스트")
    class StatusCheck {

        @Test
        @DisplayName("활성 상태인지 확인할 수 있다.")
        void isActive() {
            Member member = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);
            assertThat(member.isActive()).isTrue();
            assertThat(member.isInactive()).isFalse();
        }

        @Test
        @DisplayName("비활성 상태인지 확인할 수 있다.")
        void isInactive() {
            Member member = Member.of(name, profileImageKey, MemberAccountStatus.INACTIVE);
            assertThat(member.isInactive()).isTrue();
            assertThat(member.isActive()).isFalse();
        }
    }

    @Nested
    @DisplayName("상태 전환 테스트")
    class StatusTransition {

        @Test
        @DisplayName("비활성 상태를 활성으로 전환할 수 있다.")
        void activate() {
            Member member = Member.of(name, profileImageKey, MemberAccountStatus.INACTIVE);
            Member activated = member.activate();

            assertThat(activated.accountStatus()).isEqualTo(MemberAccountStatus.ACTIVE);
        }

        @Test
        @DisplayName("활성 상태를 비활성으로 전환할 수 있다.")
        void deactivate() {
            Member member = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);
            Member deactivated = member.deactivate();

            assertThat(deactivated.accountStatus()).isEqualTo(MemberAccountStatus.INACTIVE);
        }
    }

    @Nested
    @DisplayName("정보 업데이트 테스트")
    class Update {

        @Test
        @DisplayName("이름을 변경할 수 있다.")
        void updateName() {
            Member member = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);
            Member updated = member.updateName(MemberName.from("김아름"));

            assertThat(updated.name().value()).isEqualTo("김아름");
            assertThat(updated.profileImageKey()).isEqualTo(profileImageKey);
            assertThat(updated.accountStatus()).isEqualTo(MemberAccountStatus.ACTIVE);
        }

        @Test
        @DisplayName("프로필 이미지 키를 변경할 수 있다.")
        void updateProfileImageKey() {
            Member member = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);
            Member updated = member.updateProfileImageKey(MemberProfileImageKey.from("profile/2.png"));

            assertThat(updated.name()).isEqualTo(name);
            assertThat(updated.profileImageKey().value()).isEqualTo("profile/2.png");
            assertThat(updated.accountStatus()).isEqualTo(MemberAccountStatus.ACTIVE);
        }
    }

    @Nested
    @DisplayName("동등성 비교 테스트")
    class Equality {

        @Test
        @DisplayName("모든 필드가 같으면 동일한 멤버로 판단한다.")
        void sameValuesShouldBeEqual() {
            Member member1 = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);
            Member member2 = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);

            assertThat(member1).isEqualTo(member2);
            assertThat(member1.hashCode()).isEqualTo(member2.hashCode());
        }

        @Test
        @DisplayName("다른 필드가 하나라도 있으면 서로 다른 멤버로 판단한다.")
        void differentValuesShouldNotBeEqual() {
            Member differentName = Member.of(MemberName.from("다른이름"), profileImageKey, MemberAccountStatus.ACTIVE);
            Member differentImageKey = Member.of(name, MemberProfileImageKey.from("diff/key.png"), MemberAccountStatus.ACTIVE);
            Member differentStatus = Member.of(name, profileImageKey, MemberAccountStatus.INACTIVE);

            Member original = Member.of(name, profileImageKey, MemberAccountStatus.ACTIVE);

            assertThat(original).isNotEqualTo(differentName);
            assertThat(original).isNotEqualTo(differentImageKey);
            assertThat(original).isNotEqualTo(differentStatus);
        }
    }

}
