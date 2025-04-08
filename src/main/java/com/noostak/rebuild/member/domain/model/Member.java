package com.noostak.rebuild.member.domain.model;

import com.noostak.rebuild.member.domain.enums.MemberAccountStatus;
import com.noostak.rebuild.member.domain.model.vo.MemberName;
import com.noostak.rebuild.member.domain.model.vo.MemberProfileImageKey;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Member {

    private final MemberName name;
    private final MemberProfileImageKey profileImageKey;
    private final MemberAccountStatus accountStatus;

    private Member(MemberName name, MemberProfileImageKey profileImageKey, MemberAccountStatus accountStatus) {
        this.name = name;
        this.profileImageKey = profileImageKey;
        this.accountStatus = accountStatus;
    }

    public static Member of(MemberName name, MemberProfileImageKey profileImageKey, MemberAccountStatus accountStatus) {
        return new Member(name, profileImageKey, accountStatus);
    }

    public MemberName name() {
        return name;
    }

    public MemberProfileImageKey profileImageKey() {
        return profileImageKey;
    }

    public MemberAccountStatus accountStatus() {
        return accountStatus;
    }

    public boolean isActive() {
        return accountStatus.isActive();
    }

    public boolean isInactive() {
        return accountStatus.isInactive();
    }

    public Member activate() {
        return new Member(name, profileImageKey, MemberAccountStatus.ACTIVE);
    }

    public Member deactivate() {
        return new Member(name, profileImageKey, MemberAccountStatus.INACTIVE);
    }

    public Member updateName(MemberName newName) {
        return new Member(newName, profileImageKey, accountStatus);
    }

    public Member updateProfileImageKey(MemberProfileImageKey newProfileImageKey) {
        return new Member(name, newProfileImageKey, accountStatus);
    }
}
