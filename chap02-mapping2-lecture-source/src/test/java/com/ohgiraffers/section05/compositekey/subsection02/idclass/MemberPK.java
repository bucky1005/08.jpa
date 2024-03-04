package com.ohgiraffers.section05.compositekey.subsection02.idclass;

import java.io.Serializable;

/* 설명. IdClass 타입으로 쓰인 클래스도 1차 캐시에서 식별자로 쓰이는 객체이므로 equals와 hashCode를 오버라이딩한다. */
public class MemberPK implements Serializable {
    private int memberNo;
    private String memberId;

    public MemberPK() {
    }

    public MemberPK(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    public int getMemberNo() {
        return memberNo;
    }

    @Override
    public String toString() {
        return "MemberPK{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                '}';
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
