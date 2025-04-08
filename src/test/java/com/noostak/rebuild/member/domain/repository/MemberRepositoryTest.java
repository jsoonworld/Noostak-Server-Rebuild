package com.noostak.rebuild.member.domain.repository;

import com.noostak.rebuild.member.domain.enums.MemberAccountStatus;
import com.noostak.rebuild.member.domain.model.Member;
import com.noostak.rebuild.member.domain.model.vo.MemberName;
import com.noostak.rebuild.member.domain.model.vo.MemberProfileImageKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("MemberRepository 테스트")
class MemberRepositoryTest {

    private final FakeMemberRepository repository = new FakeMemberRepository();

    @Nested
    @DisplayName("성공 케이스")
    class SuccessCases {

        @Test
        @DisplayName("Member를 저장하고, 같은 정보를 기반으로 조회할 수 있다.")
        void saveAndRetrieveMember() {
            // given
            Member member = Member.of(
                    MemberName.from("장순"),
                    MemberProfileImageKey.from("profile-img-key"),
                    MemberAccountStatus.ACTIVE
            );

            // when
            repository.save(member);
            Optional<Member> result = repository.findBy(member);

            // then
            assertThat(result).isPresent();
            assertThat(result.get()).isEqualTo(member);
        }

        @Test
        @DisplayName("서로 다른 여러 멤버를 저장하고 각각 조회할 수 있다.")
        void saveMultipleMembersAndRetrieve() {
            // given
            Member member1 = Member.of(
                    MemberName.from("사용자A"),
                    MemberProfileImageKey.from("img-a"),
                    MemberAccountStatus.ACTIVE
            );
            Member member2 = Member.of(
                    MemberName.from("사용자B"),
                    MemberProfileImageKey.from("img-b"),
                    MemberAccountStatus.INACTIVE
            );

            // when
            repository.save(member1);
            repository.save(member2);

            Optional<Member> result1 = repository.findBy(member1);
            Optional<Member> result2 = repository.findBy(member2);

            // then
            assertThat(result1).isPresent();
            assertThat(result1.get()).isEqualTo(member1);

            assertThat(result2).isPresent();
            assertThat(result2.get()).isEqualTo(member2);
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases {

        @Test
        @DisplayName("존재하지 않는 멤버를 조회하면 빈 Optional을 반환한다.")
        void findNonExistentMember() {
            // given
            Member nonExistentMember = Member.of(
                    MemberName.from("없는멤버"),
                    MemberProfileImageKey.from("non-existent-key"),
                    MemberAccountStatus.ACTIVE
            );

            // when
            Optional<Member> result = repository.findBy(nonExistentMember);

            // then
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("null 멤버를 저장하려고 하면 예외가 발생한다.")
        void saveNullMemberThrowsException() {
            // given
            Member nullMember = null;

            // when & then
            assertThatThrownBy(() -> repository.save(nullMember))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("저장하려는 Member 객체가 null일 수 없습니다.");
        }

        @Test
        @DisplayName("null 멤버로 조회하려고 하면 예외가 발생한다.")
        void findByNullMemberThrowsException() {
            // given
            Member nullMember = null;

            // when & then
            assertThatThrownBy(() -> repository.findBy(nullMember))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("조회하려는 Member 객체가 null일 수 없습니다.");
        }
    }
}
