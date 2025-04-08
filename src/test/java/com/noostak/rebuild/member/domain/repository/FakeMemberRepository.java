package com.noostak.rebuild.member.domain.repository;

import com.noostak.rebuild.member.domain.model.Member;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FakeMemberRepository implements MemberRepository {

    private final Set<Member> storage = new HashSet<>();

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Member member) {
        if (member == null) {
            throw new NullPointerException("저장하려는 Member 객체가 null일 수 없습니다.");
        }
        storage.remove(member);
        storage.add(member);
    }

    public Optional<Member> findBy(Member target) {
        if (target == null) {
            throw new NullPointerException("조회하려는 Member 객체가 null일 수 없습니다.");
        }
        return storage.stream()
                .filter(saved -> saved.equals(target))
                .findFirst();
    }
}
