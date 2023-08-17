package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration  // 자바 코드로 스프링 빈 직접 등록
public class SpringConfig {

    // JDBC, JdbcTemplate
    private final DataSource dataSource;

    // JPA
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    // 스프링 빈 등록
    @Bean
    public MemberService memberService(){
        // 이 로직을 호출해서 스프링 빈에 등록
        // 스프링 빈에 등록된 memberRepository를 MemberService에 의존관계 주입한다.
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){  // MemberRepository는 인터페이스

        //        return new MemoryMemberRepository();     // MemoryMemberRepository는 구현체

        // Repository를 Memory에서 JDBC로 변경
        // 변경 시, SpringConfig 설정 파일을 제외한 어느 것도 바꾸지 않음! (OCP)
        // 즉, 구현체 바꿔 끼우기
//        return new JdbcMemberRepository(dataSource);

        // Jdbc Template
//        return new JdbcTemplateMemberRepository(dataSource);

        // JPA
        return new JpaMemberRepository(em);
    }
}
