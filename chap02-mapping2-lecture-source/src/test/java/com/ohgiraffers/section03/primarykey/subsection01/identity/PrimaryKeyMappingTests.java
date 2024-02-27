package com.ohgiraffers.section03.primarykey.subsection01.identity;

import com.ohgiraffers.section01.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

public class PrimaryKeyMappingTests {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    /* 설명. strategy: 자동 생성 전략을 지정
    *   - GenerationType.IDENTITY: 기본 키 생성을 데이터베이스에 위임(MySQL 또는 MariaDB의 auto_increment)
    *   - GenerationType.SEQUENCE: 데이터 베이스 시퀀스 객체 사용(Oracle의 SEQUENCE)
    *   - GenerationType.TABLE: 키 생성 테이블 사용
    *   - GenerationType.AUTO: 자동 선택(MySQL의 IDENTITY 또는 Oracle이면 SEQUENCE)*/

    @Test
    public void 식별자_매핑_테스트() {
        Member member = new Member();
//        member.setMemberNo(1);                    // auto increment 사용 중 이므로 해당 코드는 불필요
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-5678");
        member.setEmail("hong@gmail.com");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new Date());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        Member member2 = new Member();
//        member2.setMemberNo(1);
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-1111-5678");
        member2.setEmail("yu@gmail.com");
        member2.setAddress("서울시 강동구");
        member2.setEnrollDate(new Date());
        member2.setMemberRole("ROLE_MEMBER");
        member2.setStatus("Y");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();          // 트랜잭션 시작을 알림
        entityManager.persist(member);      // member를 관리해달라고 persist를 날림
        entityManager.persist(member2);      // member2를 관리해달라고 persist를 날림
        entityTransaction.commit();

//        Member selectedmember = entityManager.find(Member.class, 1);
//        System.out.println("selectedmember = " + selectedmember);

        String jpql = "SELECT A.memberNo FROM member_section03_subsection01 A";     // A.memberNo는 컬럼명이 아닌 필드의 정보가 들어감

        List<Integer> memberNoList = entityManager.createQuery(jpql, Integer.class).getResultList();     // .getResultList는 결과값이 다중행인 경우 리스트에 담아 출력할 수 있도록 함

        memberNoList.forEach(System.out::println);

    }
}
