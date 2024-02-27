package com.ohgiraffers.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/* 설명. JPQL: where절이 없는 다중행 조회 할 때 사용 */
public class SimpleJPQLTests {
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

    /* 설명. jpql의 기본 문법
            1. SELECT, UPDATE, DELETE 등의 키워드 사용은 SQL과 동일하다.
            2. INSERT는 persist() 메소드를 사용하면 된다.
            3. 키워드는 대소문자 구분하지 않지만, 엔티티와 속성은 대소문자를 구분하므로 유의한다.
            4. 엔티티 별칭을 필수로 사용해야 하며 별칭 없이 작성 시 에러가 발생한다.
     */

    /* 설명. JPQL의 사용 방법
            1. 작성한 JPQL(문자열)을 'entityManager.createQuery()' 메소드를 통해 쿼리 객체로 만든다.
            2. 쿼리 객체는 'TupeQuery', 'Query' 두 가지가 있다.
                2-1. TypeQuery: 반환할 타입을 명확하게 지칭하는 방식일 때 사용하며 쿼리 객체의 메소드 실행 결과로
                                지정한 타입이 반환된다.
                2-2. Query: 반환할 타입을 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로 Object 또는
                            Object[]이 반환된다.
            3. 쿼리 객체에서 제공하는 메소드를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.
                3-1. getSingleResult(): 결과가 정확히 한 행일 경우 사용하며 없거나 많으면 에러가 발생한다.
                3-2. getResultList(): 결과가 2행 이상일 때 사용하며 컬렉션을 반홚나다. 없으면 빈 컬렉션을 반환한다.
     */
    @Test
    public void TypeQuery를_이용한_단일행_단일열_조회_테스트() {
        String jpql = "SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 7";

        /* 설명. TypedQuery: 쿼리의 결과물의 자료형(타입)을 지정할 수 있는 쿼리 */
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);   // 조회해온 결과물을 String형 클래스의 타입으로 변경한다.

        String resultMenuName = query.getSingleResult();

        System.out.println("resultMenuName = " + resultMenuName);

        assertEquals("민트미역국", resultMenuName);
    }

    @Test
    public void Query를_이용한_단일행_단일열_조회_테스트() {
        String jpql = "SELECT menuName FROM menu_section01 WHERE menuCode = 7";
        Query query = entityManager.createQuery(jpql);

        Object resultMenuName = query.getSingleResult();

        assertTrue(resultMenuName instanceof String);
        assertEquals("민트미역국", resultMenuName);
    }

    @Test
    public void TypeQuery를_이용한_다중행_다중열_조회_테스트(){

        /* 설명. jpql에서는 entity의 별칭을 적으면 모든 속성(컬럼)을 조회하는 것이다. */
        String jpql = "SELECT m FROM menu_section01 as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> foundMenuLsit = query.getResultList();

        assertTrue(!foundMenuLsit.isEmpty());
        foundMenuLsit.forEach(System.out::println);
    }

    @Test
    public void distinct를_활용한_중복제거_여러_행_조회_테스트(){

        /* 설명. 메뉴로 존재하는 카테고리의 종류(중복제거)만 조회 */
        String jpql = "SELECT DISTINCT m.categoryCode FROM menu_section01 m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> categoryCodeList = query.getResultList();

        assertTrue(!categoryCodeList.isEmpty());
        categoryCodeList.forEach(System.out::println);
    }

    @Test
    public void in_연산자를_활용한_조회_테스트() {
        /* 설명. 카테고리 코드가 6번 or 10번인 메뉴를 조회 */
        String jpql = "SELECT m FROM menu_section01 m WHERE m.categoryCode IN (6,10)";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        assertTrue(!menuList.isEmpty());
        menuList.forEach(System.out::println);
    }

    @Test
    public void like_연산자를_활용한_조회_테스트() {
        String jpql = "SELECT m FROM menu_section01 m WHERE m.menuName LIKE '%마늘%'";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        assertTrue(!menuList.isEmpty());
        menuList.forEach(System.out::println);
    }
}
