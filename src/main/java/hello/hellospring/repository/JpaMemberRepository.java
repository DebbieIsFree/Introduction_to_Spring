package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA는 EntityManager로 모두 동작한다.
    private final EntityManager em;

    // 스프링 부트가 자동으로 만들어둔 EntityManager를 주입하면 된다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // JPA가 insert query 자동 실행 및 DB 저장 & id 생성
        em.persist(member);     // 영구 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL, 객체를 대상으로 쿼리 보내기
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }
}
