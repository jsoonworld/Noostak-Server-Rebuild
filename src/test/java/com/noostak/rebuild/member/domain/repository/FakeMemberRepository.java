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
        storage.remove(member);
        storage.add(member);
    }

    public Optional<Member> findBy(Member target) {
        return storage.stream()
                .filter(saved -> saved.equals(target))
                .findFirst();
    }
}
