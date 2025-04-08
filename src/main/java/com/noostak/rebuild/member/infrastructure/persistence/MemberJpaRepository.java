package com.noostak.rebuild.member.infrastructure.persistence;

import com.noostak.rebuild.member.domain.model.Member;
import com.noostak.rebuild.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository {

    private final SpringDataMemberJpaRepository springRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return springRepository.findById(id)
                .map(MemberMapper::toDomain);
    }

    @Override
    public void save(Member member) {
        springRepository.save(MemberMapper.toEntity(member));
    }
}

