package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
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
//    private final DataSource dataSource;

    // JPA
//    private EntityManager em;

    // 스프링 데이터 JPA
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
//        this.dataSource = dataSource;
//        this.em = em;

        // 스프링 데이터 JPA
        this.memberRepository = memberRepository;
    }

    // AOP, 공통 관심 사항 분리 --> 직접 등록, but, @Component 스캔 사용 가능
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

    // 스프링 빈 등록
    @Bean
    public MemberService memberService(){
        // 이 로직을 호출해서 스프링 빈에 등록
        // 스프링 빈에 등록된 memberRepository를 MemberService에 의존관계 주입한다.
//        return new MemberService(memberRepository());

        return new MemberService(memberRepository);
    }

    // 스프링 데이터 JPA는 memberRepository() 주석 처리
//    @Bean
//    public MemberRepository memberRepository(){  // MemberRepository는 인터페이스
//
//        //        return new MemoryMemberRepository();     // MemoryMemberRepository는 구현체
//
//        // Repository를 Memory에서 JDBC로 변경
//        // 변경 시, SpringConfig 설정 파일을 제외한 어느 것도 바꾸지 않음! (OCP)
//        // 즉, 구현체 바꿔 끼우기
////        return new JdbcMemberRepository(dataSource);
//
//        // Jdbc Template
////        return new JdbcTemplateMemberRepository(dataSource);
//
//        // JPA
////        return new JpaMemberRepository(em);
//
//    }
}
