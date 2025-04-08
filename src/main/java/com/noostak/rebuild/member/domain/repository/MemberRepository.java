package com.noostak.rebuild.member.domain.repository;

import com.noostak.rebuild.member.domain.model.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Long id);
    void save(Member member);
}
