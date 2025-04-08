package com.noostak.rebuild.member.infrastructure.persistence;

import com.noostak.rebuild.member.domain.enums.MemberAccountStatus;
import com.noostak.rebuild.member.domain.model.vo.MemberName;
import com.noostak.rebuild.member.domain.model.vo.MemberProfileImageKey;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    private MemberName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "profile_image_key"))
    private MemberProfileImageKey profileImageKey;

    @Enumerated(EnumType.STRING)
    private MemberAccountStatus accountStatus;

    private MemberJpaEntity(MemberName name, MemberProfileImageKey profileImageKey, MemberAccountStatus accountStatus) {
        this.name = name;
        this.profileImageKey = profileImageKey;
        this.accountStatus = accountStatus;
    }

    public static MemberJpaEntity of(MemberName name, MemberProfileImageKey profileImageKey, MemberAccountStatus accountStatus) {
        return new MemberJpaEntity(name, profileImageKey, accountStatus);
    }
}
