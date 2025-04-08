package com.noostak.rebuild.member.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMemberJpaRepository extends JpaRepository<MemberJpaEntity, Long> {
}

