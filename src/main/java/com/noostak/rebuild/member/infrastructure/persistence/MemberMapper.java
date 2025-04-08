package com.noostak.rebuild.member.infrastructure.persistence;

import com.noostak.rebuild.member.domain.model.Member;

public class MemberMapper {

    public static Member toDomain(MemberJpaEntity entity) {
        return Member.of(
                entity.name(),
                entity.profileImageKey(),
                entity.accountStatus()
        );
    }

    public static MemberJpaEntity toEntity(Member domain) {
        return MemberJpaEntity.of(
                domain.name(),
                domain.profileImageKey(),
                domain.accountStatus()
        );
    }
}
