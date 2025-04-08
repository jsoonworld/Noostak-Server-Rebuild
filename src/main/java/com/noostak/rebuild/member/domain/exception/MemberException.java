package com.noostak.rebuild.member.domain.exception;

import com.noostak.rebuild.global.exception.BaseException;

public class MemberException extends BaseException {
    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}
