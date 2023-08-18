package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 상속 -> extends // 인터페이스는 다중 상속 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 스프링 데이터 JPA : 인터페이스만으로 ok, 자동 등록
    // JPQL : select m from Member m where m.name = ?
    // 인터페이스 이름 만으로 개발 끝
    @Override
    Optional<Member> findByName(String name);
}
